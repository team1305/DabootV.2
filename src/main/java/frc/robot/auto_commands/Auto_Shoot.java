package frc.robot.auto_commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.RobotContainer;

/**
 *
 */
public class Auto_Shoot extends SequentialCommandGroup {
    double distance;
    double irpm;
    int loopCounter;
    int seconds;

    public Auto_Shoot(int seconds) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this.seconds = seconds;

        loopCounter = 0;

    }

public void initialize() {
    withTimeout(3);
    getDistanceAuto();
}

    // Called repeatedly when this Command is scheduled to run
    public void execute() {

        // Fire up the Shooter
        RobotContainer.shooter.setShooterRPM(irpm);
        SmartDashboard.putNumber("irpm_auto", irpm);

        SmartDashboard.putNumber("shooterRPM", RobotContainer.shooter.getShooterRPM());
        SmartDashboard.putNumber("thedistance", distance);
        double droppedIrpm = irpm - 50;

        if (RobotContainer.shooter.getShooterRPM() >= droppedIrpm) {
            // We are at speed, Turn on feeders
            RobotContainer.elevator.setElevator(0.4);

            RobotContainer.intake.setIntake(0.4);
            loopCounter = loopCounter + 1;
        }

    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
        return false;
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
        RobotContainer.intake.setIntake(0);


    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }

    private void getDistanceAuto(){
        /*distance = Robot.limelight.getDistance();

        if (distance <= 200) { // 1205
            Robot.shooter.hoodDown();
            irpm = 4000;
            Robot.shooter.setShooterPIDInfrontOfLine();

        }

        else if ((distance > 200) && (distance <= 300)) { // 120, 259
            irpm = 4000;

            Robot.shooter.hoodDown();
            Robot.shooter.setShooterPIDInitiationLine();

        }

        else if ((distance > 300) && (distance <= 350)) {// 259, 450
            irpm = 5000;
            Robot.shooter.hoodUp();
            Robot.shooter.setShooterPIDTrench();
        }

        else if ((distance > 350) && (distance <= 400)) {// 259, 450
            irpm = 5500;
            Robot.shooter.hoodUp();
            Robot.shooter.setShooterPIDTrench();
        }

        else if ((distance > 400) && (distance <= 500)) {// 259, 450
            irpm = 5750;
            Robot.shooter.hoodUp();
            Robot.shooter.setShooterPIDTrench();
        }

        else if ((distance > 500) && (distance <= 600)){//259, 450
            irpm = 5750;
            Robot.shooter.hoodDown();
            Robot.shooter.setShooterPIDTrench();
          }

        else {
            irpm = 6000;
            Robot.shooter.hoodUp();
            Robot.shooter.setShooterPIDTrenchBack();
        }
      */
    }
}
