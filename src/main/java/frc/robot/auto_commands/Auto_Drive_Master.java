package frc.robot.auto_commands;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotContainer;

/**
 *
 */
public class Auto_Drive_Master extends SequentialCommandGroup {

	double distance1;
	double AnglePowerFactor;
	double RampUpDist;
	double RampDownDist;
	double MinSpeed;
	double angle1;
	double power;
	double currPos;
	double currPos2;
	double startPos;
	double target;
	double irpm_set;

	double dif;

	double RampUpPercent;
	double SetPower;

	double speedleft;
	double speedright;

	boolean bshuffle;
	double shuffleduration;

    double iloops;

	public Auto_Drive_Master(double angle1, double distance1, double power, double minpower, double rampup,
			double rampdown, boolean bshuffle, double shuffleduration, double irpm_set) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);

		 addRequirements(RobotContainer.drive);

		this.distance1 = distance1 * RobotContainer.drive.getgearratio(); // converts distance to encoder values
		this.angle1 = angle1;
		this.power = power;
        this.irpm_set = irpm_set;

		iloops = 0;
		this.bshuffle = bshuffle;
		this.shuffleduration = shuffleduration * 50;

		this.startPos = -1;

		MinSpeed = minpower; // set to just enough power to move bot
		AnglePowerFactor = .01; ///0.02 at york, was 0.5 0.1 = 10%
		RampUpDist = rampup * RobotContainer.drive.getgearratio();
		RampDownDist = rampdown * RobotContainer.drive.getgearratio();
		
	}

	// Called just before this Command runs the first time
	public void initialize() {
		currPos = -.1;
		//RobotContainer.drive.resetEncoder();
		startPos = -.1;
        
		iloops = 0;


		// Robot.drive.LowGear();
	
	}

	// Called repeatedly when this Command is scheduled to run
	public void execute() {
       iloops = iloops + 1;

        if (startPos == -.1) {
			// This is first time execute loop runs
			startPos = RobotContainer.drive.getDistance();
			target = startPos + distance1;
	
			if (RobotContainer.getdebug()) {
				SmartDashboard.putString("Starting Auto Drive", "yes");
				SmartDashboard.putNumber("Auto Drive Start", startPos);
				SmartDashboard.putNumber("Target Auto Drive", target);
			 }
		}

		// New Shuffle while driving code
		if (bshuffle) {
			if (iloops <= shuffleduration) {
				RobotContainer.intake.setIntake(0.5); 
				RobotContainer.elevator.setElevator(0.4); 
				RobotContainer.shooter.setShooter(-0.3);
			} else { // turn off shuffle
				RobotContainer.intake.setIntake(0); 
				//if (irpm_set == 0) {
				   RobotContainer.elevator.setElevator(0);
			    //} 
				RobotContainer.shooter.setShooter(0);

				// if irpm_set > 0 Then do prep and warm up shooter
				/*if (irpm_set > 0) { 
					if ( (iloops > shuffleduration) && (iloops <= (shuffleduration + 4)) ) {
						RobotContainer.elevator.setElevator(-0.2); // prep ball
					} else {
						// Warm up shooter
						if (iloops > (shuffleduration + 4)) {
						   RobotContainer.elevator.setElevator(0);
						   RobotContainer.shooter.setShooterRPM(irpm_set);
						} 			
					}
				}*/
			}
		}

		currPos = RobotContainer.drive.getDistance();
		currPos2 = RobotContainer.drive.getDistance2();

		SmartDashboard.putNumber("Auto Drive Curr Pos", currPos);
		SmartDashboard.putNumber("Auto Drive RampDownDist", RampDownDist);
		
		if (target > startPos) { // go forward

			if (currPos <= (startPos + RampUpDist)) {  //ramp up
				dif = angle1 - RobotContainer.drive.gyroGetAngle();
			    //dif = 0;

				RampUpPercent = (currPos / (startPos + RampUpDist));
				SetPower = (MinSpeed + ((power - MinSpeed) * RampUpPercent));

				speedleft = SetPower + (dif * AnglePowerFactor); // add or subtract power to left
				speedright = SetPower - (dif * AnglePowerFactor); // add or subtract power to right

				//if (speedleft > MinSpeed) { // required if angle difference is large so we do not get negative speeds
				//	speedleft = Math.min(speedleft, power);
				//} else {
				//	speedleft = MinSpeed;
				//}

				if (speedright > MinSpeed) {
					speedright = Math.min(speedright, power);
				} else {
					speedright = MinSpeed;
				} // end of ramp up

			} else if (currPos >= (target - RampDownDist)) {  //ramp down
				dif = angle1 - RobotContainer.drive.gyroGetAngle();
			    //dif = 0;

				//RampUpPercent = (currPos / (target - RampDownDist));
				RampUpPercent = ((target - currPos) / RampDownDist);
				SetPower = (MinSpeed + ((power - MinSpeed) * RampUpPercent));

				//SetPower = (0.4);

				speedleft = SetPower + (dif * AnglePowerFactor); // add or subtract power to left
				speedright = SetPower - (dif * AnglePowerFactor); // add or subtract power to right
				
//				if (speedleft > MinSpeed) { // required if angle difference is large so we do not get negative speeds
//					speedleft = Math.min(speedleft, power);
//				} else {
//					speedleft = MinSpeed;
//				}
//
//				if (speedright > MinSpeed) {
//					speedright = Math.min(speedright, power);
//				} else {
//					speedright = MinSpeed;
//				} // end of ramp 

			} else {
				// drive full speed * direction;
				SetPower = power;
				dif = angle1 - RobotContainer.drive.gyroGetAngle();
				//dif = 0;

				speedleft = SetPower + (dif * AnglePowerFactor); // add or subtract power to left
				speedright = SetPower - (dif * AnglePowerFactor); // add or subtract power to right

				//if (speedleft > MinSpeed) { // required if angle difference is large so we do not get negative speeds
				//	speedleft = Math.min(speedleft, power);
				//} else {
				//	speedleft = MinSpeed;
				//}

				if (speedright > MinSpeed) {
					speedright = Math.min(speedright, power);
				} else {
					speedright = MinSpeed;
				}

			}
			if (RobotContainer.getdebug()) {

			   SmartDashboard.putNumber("AutoTankLeft", speedleft);
		       SmartDashboard.putNumber("AutoTankRight", speedright);
			}
			RobotContainer.drive.tankdrive(speedleft, speedright);
	


		} else { // go backwards
			if ((currPos <= startPos) && (currPos >= (startPos - RampUpDist))) {
				dif = angle1 + RobotContainer.drive.gyroGetAngle();
				//dif = 0;

				RampUpPercent = (currPos / (startPos - RampUpDist));
					
				SetPower = (MinSpeed + ((power - MinSpeed) * RampUpPercent));

				speedleft = SetPower - (dif * AnglePowerFactor); // add or subtract power to left
				speedright = SetPower + (dif * AnglePowerFactor); // add or subtract power to right

				//speedleft = SetPower;// + (dif * AnglePowerFactor); // add or subtract power to left
				//speedright = SetPower;// - (dif * AnglePowerFactor); // add or subtract power to right

				if (RobotContainer.getdebug()) {
					SmartDashboard.putNumber("initial speedleft", speedleft);
					SmartDashboard.putNumber("initial speedright", speedright);
				 }

				//if (speedleft > MinSpeed) { // required if angle difference is large so we do not get negative speeds
				//	speedleft = Math.min(speedleft, power);
				//} else {
				//	speedleft = MinSpeed;
				//}

				//if (speedright > MinSpeed) {
				//	speedright = Math.min(speedright, power);
				//} else {
			//		speedright = MinSpeed;
			//	} // end of ramp up

			   

			} else if (currPos <= (target + RampDownDist)) {
				dif = angle1 +  RobotContainer.drive.gyroGetAngle();
				//dif = 0;

				//RampUpPercent = (currPos / (target + RampDownDist));
				
				RampUpPercent = (target - currPos) / (0 - RampDownDist);
				
				SetPower = (MinSpeed + ((power - MinSpeed) * RampUpPercent));
				//SetPower = -0.4;
				//speedleft = SetPower - (dif * AnglePowerFactor); // add or subtract power to left
				//speedright = SetPower + (dif * AnglePowerFactor); // add or subtract power to right
				speedleft = SetPower;// + (dif * AnglePowerFactor); // add or subtract power to left
				speedright = SetPower;// - (dif * AnglePowerFactor); // add or subtract power to right

				if (RobotContainer.getdebug()) {
					SmartDashboard.putNumber("rampdown speedleft", speedleft);
					SmartDashboard.putNumber("rampdown speedright", speedright);
				 }
				//if (speedleft > MinSpeed) { // required if angle difference is large so we do not get negative speeds
				//	speedleft = Math.min(speedleft, power);
				//} else {
				//	speedleft = MinSpeed;
				//}

				//if (speedright > MinSpeed) {
				//	speedright = Math.min(speedright, power);
				//} else {
				//	speedright = MinSpeed;
				//} // end of ramp up


			} else {
				// drive full speed * direction;
	SmartDashboard.putString("Backwards", "true");		
				SetPower = power;
				dif = angle1 +  RobotContainer.drive.gyroGetAngle();
				//dif = 0;

				speedleft = SetPower - (dif * AnglePowerFactor); // add or subtract power to left
				speedright = SetPower + (dif * AnglePowerFactor); // add or subtract power to right
				
				//speedleft = SetPower;// + (dif * AnglePowerFactor); // add or subtract power to left
				//speedright = SetPower;// - (dif * AnglePowerFactor); // add or subtract power to right

				/*if (speedleft > MinSpeed) { // required if angle difference is large so we do not get negative speeds
					speedleft = Math.min(speedleft, power);
				} else {
					speedleft = MinSpeed;
				}

				if (speedright > MinSpeed) {
					speedright = Math.min(speedright, power);
				} else {
					speedright = MinSpeed;
				}*/
			}
			
			if (RobotContainer.getdebug()) {
			   SmartDashboard.putNumber("speedleft", speedleft);
			   SmartDashboard.putNumber("speedright", speedright);
			}

			RobotContainer.drive.tankdrive(speedleft, speedright);
			
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	public boolean isFinished() {
		if (RobotContainer.getdebug()) {
		   SmartDashboard.putNumber("currPos", currPos);
		   SmartDashboard.putNumber("currPos2", currPos2);
		   SmartDashboard.putNumber("target", target);
		}


		if (target >= startPos) { // going forward
	
			
			return (currPos >= target);

		} else {
			//RobotContainer.drive.tankdrive(0,0);
	
			return (currPos <= target);
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		RobotContainer.drive.tankdrive(0,0);
		if (bshuffle) { // turn it off just in case
			RobotContainer.intake.setIntake(0); 
      
		}
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
