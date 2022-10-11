/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Subsystem_Drivebase;
import frc.robot.subsystems.Subsystem_Intake;
import frc.robot.subsystems.Subsystem_LED;
import frc.robot.subsystems.Subsystem_Limelight;
import frc.robot.subsystems.Subsystem_Shooter;

public class Command_ai_loop extends CommandBase {
  /**
   * Creates a new Command_ai_loop.
   */
  private final Subsystem_Shooter shooterSub;
  private final Subsystem_Limelight limelightSub ;
  //private final Subsystem_Intake intakeSub ;
  private final Subsystem_LED ledSub;
  //private final Subsystem_Drivebase driveSub;

  private String cstate;
  
  private double x;
  //private int izone;
  private int irpm;
  //private boolean btarget;
  //private double zone1threshold = 50; //  pixel width
  public double distance;
  //private int iloops = 0;
  private int ipreploops = 0;
  private boolean lowgoal = false;
  private int isuccess = 0; 
  private int lowgoalrpm = 2500;

  private int irpm_tolerance;
  private int irpm_threshold;

  public Command_ai_loop(Subsystem_Shooter shooter, Subsystem_Limelight limelight,Subsystem_Intake intake,Subsystem_LED led, Subsystem_Drivebase drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    shooterSub = shooter;
    limelightSub = limelight;
   // intakeSub = intake;
    ledSub = led;
    //driveSub = drive;
    addRequirements(shooterSub, limelightSub, ledSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    cstate = "HUNT";
    //izone = 1;
    irpm = 0;
   // btarget = false;
    isuccess = 0;
    lowgoal = false;
    SmartDashboard.putString("AI_LOOP", "INITIALIZED" );
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // AI Loop, States HUNT, PREP, SHOOT

     // Active Colour Loop
     set_colors(); // Intake = White, Target = Green, otherwise alliance color

     //Vision tracking code, activates when right bumper is pressed
     if (RobotContainer.getJoystickDriver().getRawButton(6)) { // Driver RB
  
      if (RobotContainer.Limelight.isLimelightOn() == false){
        RobotContainer.Limelight.limelightOn();
      }

       //AI  STATE loop
       switch (cstate) {  
       case "HUNT" : 
         // Use Limelight to move to target
         ipreploops = 0;

         x = RobotContainer.Limelight.get_Tx();
         //x = x + Robot.limelight.getOffsetRatio();
         //if (RobotContainer.getdebug()) {
            SmartDashboard.putNumber("x ai loop", x);
         //}

         RobotContainer.drive.turnRobotToAngle_New(x);

         if (Math.abs(x) <= 0.5){ // 1
          // Calc Distance away so we know zone 1 or zone 2

          isuccess = isuccess + 1;
         
         // izone = 1; // default
        
          distance = RobotContainer.Limelight.getDistance();
          if (RobotContainer.getdebug()) {
            SmartDashboard.putNumber("thedistance", distance );
          }

          if (distance > 0){
            if (isuccess >= 5) {
              ipreploops = 0;
              lowgoal = false;
              
              if (lowgoal) {
                irpm = lowgoalrpm; 
             } else {
                irpm = RobotContainer.shooter.getAutoRPM(distance);
             }
             
              cstate = "PREP";
           
            }
            //if (RobotContainer.getdebug()) {
            //   SmartDashboard.putNumber("isuccess", isuccess);
           // }
          } else { // Distance is zero, no target, lets assume low goal target
            //lowgoal = true;
            //irpm = lowgoalrpm;
            //cstate = "PREP";

          }
        }

         break;
        case "PREP" : // This backs up the ball a little bit
           //if (RobotContainer.getdebug()) {   
              //SmartDashboard.putString("AI_LOOP", "IN PREP LOOP" );
           //}
           
           if (distance >= 152) {
              RobotContainer.shooter.ShooterUp();
           } else {
              RobotContainer.shooter.ShooterDown();
           }

           ipreploops = ipreploops + 1;

           if (ipreploops <= 10) {
              RobotContainer.elevator.setElevator(-0.2);
           } else {
              RobotContainer.elevator.setElevator(0);
              cstate = "SHOOT";
           }
        break;
        case "SHOOT" : 
           //if (RobotContainer.getdebug()) {   
              //SmartDashboard.putString("AI_LOOP", "IN SHOOT LOOP" );
           //}
           
           // Set to target RPM
           RobotContainer.shooter.setShooterRPM(irpm); 
            
           // Added March 21, 2022
           // For far shots we may want a threshold that accepts a lower rpm so the ball shoots if battery is low?

           irpm_threshold = 4700;
           irpm_tolerance = 0;

           if (irpm >= irpm_tolerance) {
            irpm_threshold = 50;
           } else {
            irpm_threshold = 0;
           }

           if (RobotContainer.shooter.getShooterRPM() >= (irpm - irpm_tolerance)) {
             RobotContainer.elevator.setElevator(0.5); // 0.4
           } else {
             RobotContainer.elevator.setElevator(0);
           }
            
           
        break;
      }
      
     } 
     else { // button not pressed
        if ((cstate == "SHOOT") || (cstate == "PREP")){ // RECOVERY STATE
           // Button no longer pressed but last state was shoot
           // Turn off Shooter and Feeders
           RobotContainer.shooter.setShooter(0);
           RobotContainer.elevator.stopElevator();
           RobotContainer.shooter.ShooterDown();
           
           //RobotContainer.led.setBlack();
           //RobotContainer.Limelight.limelightOff();

           cstate = "HUNT";
           //izone = 1;
           irpm = 0;

        } else { // Reset back to HUNT State
           cstate = "HUNT";
           //izone = 1;
           irpm = 0;
           if (RobotContainer.Limelight.isLimelightOn()){
            RobotContainer.Limelight.limelightOff();
           }
        }

     }

  }

  protected void set_colors() {

    
    if (RobotContainer.intake.isIntakeOn() )  { // Intake On
      
        RobotContainer.Led.setWhite();
        
    } else if (RobotContainer.Limelight.is_Target() ){ // Have Target
        RobotContainer.Led.setGreen();
          
    } else { // Lights Off
      if (DriverStation.getAlliance() == Alliance.Red) {
        RobotContainer.Led.setRed(); 
      } else {
        RobotContainer.Led.setBlue();
       
      }
        //RobotContainer.Led.setLavaWave();
        //RobotContainer.Led.setBlack();
      
    }
  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

 
}
