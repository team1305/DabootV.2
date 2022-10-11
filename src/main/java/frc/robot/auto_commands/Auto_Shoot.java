package frc.robot.auto_commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
//import frc.robot.Robot;
import frc.robot.RobotContainer;

/**
 *
 */
public class Auto_Shoot extends SequentialCommandGroup {
    double distance;
    double irpm;
    double loopCounter;
    double seconds;

    public Auto_Shoot(double seconds) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        //addRequirements(RobotContainer.shooter);
        //addRequirements(RobotContainer.elevator);
        
        this.seconds = seconds;

        loopCounter = 0;
        //if (RobotContainer.getdebug()) {
        //   SmartDashboard.putString("AUTOSHOOT", "BASE INIT");
       // }
    }

public void initialize() {
    //withTimeout(5); // never shoot longer than 5 seconds
    irpm = 0;
    //if (RobotContainer.getdebug()) {
    //  SmartDashboard.putString("AUTOSHOOT", "INITIALIZED");
    //}
}

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
        loopCounter = loopCounter + 1;
        
        if (irpm == 0) {
            getDistanceAuto(); // Calls Shooter.getAutoRPM to get rpm
        }

        if (irpm > 0) {

        
        // Fire up the Shooter
        RobotContainer.shooter.setShooterRPM(irpm);
        
        if (RobotContainer.getdebug()) {
    
           SmartDashboard.putNumber("irpm_auto", irpm);

           SmartDashboard.putNumber("shooterRPM", RobotContainer.shooter.getShooterRPM());
           SmartDashboard.putNumber("thedistance", distance);
        }

        double droppedIrpm = irpm - 50;
        
        if (RobotContainer.shooter.getShooterRPM() >= droppedIrpm) {
            // We are at speed, Turn on feeders
            RobotContainer.elevator.setElevator(0.4);

            //RobotContainer.intake.setIntake(0.4);
            } else {
          RobotContainer.elevator.setElevator(0);

        }
       }
    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
        
        if (loopCounter >= (seconds*50)) {
          RobotContainer.shooter.stopShooter();
          RobotContainer.elevator.setElevator(0);
          if (RobotContainer.getdebug()) {
     
             SmartDashboard.putString("AUTOSHOOT", "FINISHED");
          }
          return true;
         } else {
            return false;
        }
        /*if (isTimedOut()) {
            return true;
        } else {
           if (loopCounter >= (seconds*20)) {
               return true;

           } else {
               return false;
           }
        }*/
    }

    // Called once after isFinished returns true
    protected void end() {
        RobotContainer.shooter.stopShooter();
        RobotContainer.elevator.setElevator(0);
        //RobotContainer.intake.setIntake(0);


    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        //end();
    }

    private void getDistanceAuto(){
     distance = RobotContainer.Limelight.getDistance();
    
     irpm = RobotContainer.shooter.getAutoRPM(distance);

       
          if (distance >= 150) {
            RobotContainer.shooter.ShooterUp();
          } else {
           RobotContainer.shooter.ShooterDown();
            
          }

    }
}
