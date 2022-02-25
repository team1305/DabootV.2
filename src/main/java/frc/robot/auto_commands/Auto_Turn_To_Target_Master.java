package frc.robot.auto_commands;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.RobotContainer;

/**
 *
 */
public class Auto_Turn_To_Target_Master extends SequentialCommandGroup {

	double x;
	int isuccess;

	public Auto_Turn_To_Target_Master() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);

		// requires(Robot.drive);

	}

	// Called just before this Command runs the first time
	public void initialize() {
		//Robot.drive.LowGear();
		RobotContainer.Limelight.limelightOn();
		isuccess = 0;

	}

	// Called repeatedly when this Command is scheduled to run
	public void execute() {
		x = RobotContainer.Limelight.get_Tx();
		// x = x + Robot.limelight.getOffsetRatio();
		// SmartDashboard.putNumber("x ai loop", x);

		RobotContainer.drive.turnRobotToAngleAuto(x);

	}

	// Make this return true when this Command no longer needs to run execute()
	public boolean isFinished() {
		if (Math.abs(x) <= 1) {
			// Calc Distance away so we know zone 1 or zone 2

			isuccess = isuccess + 1;


			if (isuccess >= 4) {//5
				return true;

			} else {
				return false;
			}
		}

		else{
			return false;
		}

	}

	// Called once after isFinished returns true
	protected void end() {
		RobotContainer.drive.DriveStop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
