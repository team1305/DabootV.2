package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auto_commands.Auto_Drive_Master;
import frc.robot.auto_commands.Auto_Drive_Stop;
import frc.robot.auto_commands.Auto_Finished;
import frc.robot.auto_commands.Auto_Intake_Elevator_Prep;
import frc.robot.auto_commands.Auto_Intake_Off;
import frc.robot.auto_commands.Auto_Intake_On;
import frc.robot.auto_commands.Auto_Intake_Shuffle;
import frc.robot.auto_commands.Auto_Reset_Encoders;
import frc.robot.auto_commands.Auto_Reset_Gyro;
import frc.robot.auto_commands.Auto_Shoot;
//import frc.robot.auto_commands.Auto_Shooter_Warm_Up;
import frc.robot.auto_commands.Auto_Tank_Rotate_Master;
import frc.robot.auto_commands.Auto_Turn_To_Target_Master;
import frc.robot.auto_commands.Auto_Wait_Seconds;

/**
 *
 */
public class AutoCommands extends SequentialCommandGroup {

    public AutoCommands(int commandtorun) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        // Set Robot Color depending on Alliance
        /*
         * if (DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Blue)
         * { m_alliance = "BLUE"; Robot.rgbledCAN.LEDoff(); Robot.rgbledCAN.LEDblue(); }
         * else { m_alliance = "RED"; Robot.rgbledCAN.LEDoff();
         * Robot.rgbledCAN.LEDred(); }
         */

        // addSequential(new Auto_Reset_Encoders()); // reset drive encoders

        if (commandtorun == 1) { // 2 ball auto, straight back and shoot
            
            addCommands(  // Auto Drive doesnt like 0.2 power
             // Intake On
             new Auto_Intake_On(),
             // Back up
             new Auto_Reset_Encoders(),
             new Auto_Drive_Master(0, 60, 0.6, 0.4, 10, 12, false, 0, 0),
             //new Auto_Drive_Stop(),
             new Auto_Drive_Stop(),
             //new Auto_Wait_Seconds(1), 
             // Intake Off
             new Auto_Intake_Off(),
             new Auto_Wait_Seconds(1),
             new Auto_Intake_Shuffle(2), // 1.5
             //new Auto_Wait_Seconds(1),
             //Turn to Target
             new Auto_Turn_To_Target_Master(),
             //new Auto_Wait_Seconds(0.5),
             new Auto_Intake_Elevator_Prep(0.15),
             // Shoot
             new Auto_Shoot(4), // 3
  
             new Auto_Finished()
            
 /*           
            // Intake On
                new Auto_Intake_On(),
                // Back up
                new Auto_Reset_Encoders(),
                new Auto_Drive_Master(0, 60, 0.5, 0.3, 2, 10, false, 0),
                //new Auto_Drive_Stop(),
                new Auto_Drive_Stop(),
                //new Auto_Wait_Seconds(1), // whats going on with this command
                // Intake Off
                new Auto_Intake_Off(),
                new Auto_Wait_Seconds(0.5),
                new Auto_Intake_Shuffle(2.5),
                //new Auto_Wait_Seconds(1),
                //Turn to Target
                new Auto_Turn_To_Target_Master(),
                //new Auto_Wait_Seconds(0.5),
                new Auto_Intake_Elevator_Prep(0.15),
                // Shoot
                new Auto_Shoot(3),
   */         
            );
        }
              
        if (commandtorun == 2) { // 4 ball auto, straight back,  then get by human player
            addCommands(  // Auto Drive doesnt like 0.2 power
                // Intake On
                new Auto_Intake_On(),
                // Back up
                new Auto_Reset_Encoders(),
                new Auto_Drive_Master(-5, 60, 0.7, 0.4, 10, 12, false, 0, 0),
                //new Auto_Drive_Stop(),
                new Auto_Drive_Stop(),
                //new Auto_Wait_Seconds(1), 
                // Intake Off
                new Auto_Intake_Off(),
                new Auto_Wait_Seconds(0.3), // .5 at york
                 new Auto_Intake_Shuffle(1), // 1.5
                //new Auto_Wait_Seconds(1),
                //Turn to Target
                new Auto_Turn_To_Target_Master(),
                //new Auto_Wait_Seconds(0.5),
                new Auto_Intake_Elevator_Prep(0.15),
                // Shoot
                new Auto_Shoot(2.7), // 3
 
                new Auto_Intake_On(),
                //new Auto_Tank_Rotate_Master(13, 0.4, false, 0), // 12 // 10
                new Auto_Drive_Master(13, 160, 0.7, 0.4, 10, 30, false, 0, 0),  // 171 // 177 pm 1  // 12, 10, 180
                //new Auto_Drive_Master(10, 120, 0.6, 0.4, 0, 10),
                //new Auto_Drive_Stop(),
                new Auto_Drive_Stop(),
                new Auto_Wait_Seconds(1),
                new Auto_Intake_Off(),
                //new Auto_Wait_Seconds(0.5),
                //new Auto_Intake_Shuffle(2),
                //new Auto_Wait_Seconds(1),
                //Turn to Target
             
                new Auto_Drive_Master(5, -150, -0.75, -0.4, 10, 25, true, 1, 3800), // -160 and ramp down 20 at york// shuffle is on for 1.5 seconds 
         
                new Auto_Turn_To_Target_Master(),
                //new Auto_Wait_Seconds(0.5),
                new Auto_Intake_Elevator_Prep(0.15),
                // Shoot
                new Auto_Shoot(3),
 
                new Auto_Finished()
            );
        }

        if (commandtorun == 3){ //3 ball auto 
            addCommands(  // Auto Drive doesnt like 0.2 power
                // Intake On
                new Auto_Intake_On(),
                // Back up
                new Auto_Reset_Encoders(),
                new Auto_Drive_Master(0, 60, 0.7, 0.4, 10, 12, false, 0, 0),
                //new Auto_Drive_Stop(),
                new Auto_Drive_Stop(),
                //new Auto_Wait_Seconds(1), 
                // Intake Off
                new Auto_Intake_Off(),
                new Auto_Wait_Seconds(0.3), // .5 at york
                 new Auto_Intake_Shuffle(1), // 1.5
                //new Auto_Wait_Seconds(1),
                //Turn to Target
                new Auto_Turn_To_Target_Master(),
                //new Auto_Wait_Seconds(0.5),
                new Auto_Intake_Elevator_Prep(0.15),
                // Shoot
                new Auto_Shoot(2.7), // 3
 
 
            new Auto_Tank_Rotate_Master(-120, 0.5, false, 0),
            new Auto_Drive_Stop(),
            new Auto_Reset_Gyro(),
            new Auto_Intake_On(),
            
            
            new Auto_Drive_Master(0, 110, 0.7, 0.4, 20, 20, false, 0, 0),
            new Auto_Drive_Stop(),
            new Auto_Intake_Off(),
            //new Auto_Wait_Seconds(0.5),
            //new Auto_Intake_Shuffle(1.5),
            
            new Auto_Tank_Rotate_Master(65, 0.5, true, 1.5),
            //new Auto_Drive_Master(65, 12, 0.4, 0.4, 0, 0, false, 0),
            
            //Turn to Target
            new Auto_Turn_To_Target_Master(),
            //new Auto_Wait_Seconds(0.5),
            new Auto_Intake_Elevator_Prep(0.15),
            // Shoot
            new Auto_Shoot(3),
            


           new Auto_Finished()
        );
        
        /*
            addSequential(new Auto_Intake_On());
            addSequential(new Auto_Drive_Master(0, -120, 0.5, 0.2, 10, 10)); //Drive add pick up two from opposite trench
            addSequential(new Auto_Wait_Seconds(0.5)); //Wait
            addSequential(new Auto_Intake_Off());//Turn of intake
            addSequential(new Auto_Drive_Master(0, 24, 0.5, 0.2, 5, 5));
            addSequential(new Auto_Tank_Rotate_Master(45, 24, 0.5, 0.3, 10, 10)); //Curve so that parallel to generator
            addSequential(new Auto_Drive(45, 120, 0.7, 0.4, 10));// Drive parallel to generator
            addSequential(new Auto_Tank_Rotate_Master(45, 24, 0.5, 0.3, 10, 10));
            addSequential(new Auto_Shooter_Warm_Up());//Warm up shooter
            addSequential(new Auto_Turn_To_Target()); //Aim
            addSequential(new Auto_Shoot()); //Fire
            //addSequential(new Auto_Drive_Curve(45, 0, 0.5, 0.3, 10, 10)); //Turn back to angle
            //addSequential(new Auto_Drive_Curve(0, 24, 0.5, 0.3, 10, 10)); //Turn to 0
            addSequential(new Auto_Intake_On());// Intake on to pick up two from generator
            addSequential(new Auto_Drive(0, -12, 0.7, 0.4, 10));//Drive back
            addSequential(new Auto_Wait_Seconds(1)); //Wait
            addSequential(new Auto_Drive(0, 12, 0.7, 0.4, 10)); //Move forwards
            addSequential(new Auto_Drive_Curve(-60, -24, 0.5, 0.3, 10, 10)); //Curve to last ball
            addSequential(new Auto_Intake_Off()); //Intake off
            addSequential(new Auto_Shooter_Warm_Up()); //Warm up
            addSequential(new Auto_Drive_Curve(0, 48, 0.5, 0.3, 10, 10)); //Move forwards and get back to 0
            addSequential(new Auto_Turn_To_Target());// Aim
            addSequential(new Auto_Shoot()); //Fire
            addSequential(new Auto_Finished());
            */
            
            


        }

        if (commandtorun == 4){ // 5 ball Auto
            // Not Used Yet - Einstein??
        }


   

        

    }

 
   // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
