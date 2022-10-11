// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;



import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.Command_ai_loop;

public class Subsystem_Shooter extends SubsystemBase {
  /** Creates a new Subsystem_Intake. */


  private final static WPI_TalonFX mtShooter1 = Constants.mtShooter1;
  private final static WPI_TalonFX mtShooter2 = Constants.mtShooter2;

  private final static int soffset = -150; //-75 // 0 at York, +25 at North Bay

  /*private double kP_Shooter;
  private double kI_Shooter;
  private double kD_Shooter;
  private double kF_Shooter;
*/

private final static Solenoid slndShooter = Constants.slndShooter;

  public boolean bShooterUp;

  public Subsystem_Shooter() {
    /*kP_Shooter = Constants.kP_SHOOTER_INFRONT_OF_LINE;
    kI_Shooter = Constants.kI_SHOOTER_INFRONT_OF_LINE;
    kD_Shooter = Constants.kD_SHOOTER_INFRONT_OF_LINE;
    kF_Shooter = Constants.kF_SHOOTER_INFRONT_OF_LINE;
*/
    
    bShooterUp = false;
    mtShooter1.configFactoryDefault();
    mtShooter2.configFactoryDefault();
    mtShooter1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    mtShooter1.setInverted(false);
    mtShooter2.setSensorPhase(true);

    mtShooter1.setInverted(true);
    mtShooter1.setSensorPhase(true);

   // Trying to get brake mode working
   mtShooter1.setNeutralMode(NeutralMode.Coast);
   mtShooter2.setNeutralMode(NeutralMode.Coast);

   mtShooter1.config_kP(0, Constants.kP_SHOOTER_INFRONT_OF_LINE);
   mtShooter1.config_kI(0, Constants.kI_SHOOTER_INFRONT_OF_LINE);
   mtShooter1.config_kD(0, Constants.kD_SHOOTER_INFRONT_OF_LINE);
   mtShooter1.config_kF(0, Constants.kF_SHOOTER_INFRONT_OF_LINE);
   mtShooter1.config_IntegralZone(0, Constants.kIZone_SHOOTER);


   mtShooter1.clearStickyFaults();
   mtShooter2.clearStickyFaults();

   mtShooter2.follow(mtShooter1);

   mtShooter1.configStatorCurrentLimit(Constants.currentLimitConfig, 30);
   mtShooter2.configStatorCurrentLimit(Constants.currentLimitConfig, 30);


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (RobotContainer.getdebug()) {
       SmartDashboard.putNumber("Shooter Rotations", getShooterRotations());
       SmartDashboard.putNumber("Shooter RPM", getShooterRPM());
       SmartDashboard.putNumber("Shooter Output Percent", mtShooter1.getMotorOutputPercent());
    }

    setDefaultCommand(new Command_ai_loop(RobotContainer.shooter, RobotContainer.Limelight, RobotContainer.intake, RobotContainer.Led, RobotContainer.drive));

  }

  public int getAutoRPM(double thedistance) {
    int irpm = 0;

    // irpm = 5000; // approx 10 feet
           //irpm = 3000; // low goal
           // irpm = 5600 and hoodup from launch pad
           // irpm = 4200 at 74 inches
           // irpm 4500 = at 104 inches
           // irpm = 4700  at 111
           // irpm = 4900 at 128 (second ball may need 100 more)
           // launchad = 166 inches
          

    if (thedistance < 60) {
      irpm = 4600 + soffset; // 4800 // 3000
    } else if ((thedistance >= 60) && (thedistance < 67)) {
       irpm = 3800 + soffset; 
     } else if ((thedistance >= 67) && (thedistance < 81)) {
       irpm = 3900 + soffset; 
     } else if ((thedistance >= 81) && (thedistance < 93)) {
       irpm = 4000 + soffset; 
      } else if ((thedistance >= 93) && (thedistance < 103)) {
        irpm = 4100 + soffset; 
      } else if ((thedistance >= 103) && (thedistance < 113)) {
       irpm = 4080 + soffset; //4200 //4140 at provincals
     } else if ((thedistance >= 113) && (thedistance < 122)) {
       irpm = 4250 + soffset; 
     } else if ((thedistance >= 122) && (thedistance < 132)) {//tested
       irpm = 4250 + soffset; // does this one need a bit more , used to be 4250
      } else if ((thedistance >= 132) && (thedistance < 139)) {
       irpm = 4350;  // 4400 + offset // front bumber on line - distance is 134 ish
     } else if ((thedistance >= 139) && (thedistance < 149)) {//tested
       irpm = 4470 + soffset - 400; //4475
      } else if ((thedistance >= 149) && (thedistance < 151)) {//152 shooter bar engages possibly split
        irpm = 4473 + soffset; 
      } else if ((thedistance >= 151) && (thedistance < 159)) {//152 shooter bar engages possibly split
        irpm = 4500 + soffset; 
      } else if ((thedistance >= 159) && (thedistance < 169)) {//tested
        irpm = 4450 + soffset; 
     } else if ((thedistance >= 169) && (thedistance < 180)) {//tested
       irpm = 4550 + soffset; 
      } else if ((thedistance >= 180) && (thedistance < 190)) {//tested
        irpm = 4650 + soffset; 
      } else if ((thedistance >= 190) && (thedistance < 200)) {//tested
        irpm = 4700 + soffset; 
      } else if ((thedistance >= 200) && (thedistance < 211)) {//tested
        irpm = 4800 + soffset; 
      } else if ((thedistance >= 211) && (thedistance < 221)) {//tested
        irpm = 4900 + soffset; 
      } else if ((thedistance >= 221) && (thedistance < 236)) {//tested
        irpm = 4950 + soffset; //5000
    }  else if(thedistance >= 236) { // not sure what vakue to use after 236 inches away
       irpm = (int)Math.round(5100 + ((thedistance - 236) * 8) + soffset); // every 10 inches + 100
       if (irpm >= 5500) {
         irpm = 5500;
       }
    }

    //if (RobotContainer.getdebug()) {
      System.out.println("AI SHOOT: " + RobotContainer.getTimeStamp() + ": " + Double.toString(thedistance) + " RPM: " + Integer.toString(irpm) );
    //} 
    // Lets log the request for analysis

    return irpm;
  }

  public double getShooterRotations() {
    return mtShooter1.getSelectedSensorPosition() / Constants.SHOOTER_OUTPUT_TO_ENCODER_RATIO / Constants.TICKS_PER_ROTATION;
    // sensor / 1/ 2048
  }

  public void setShooter(double speed) {
    //mtIntake.set(ControlMode.PercentOutput, speed);
    mtShooter1.set(speed);
    //mtShooter2.set(speed);

    
  }

  public double getShooterRPM() {
    return mtShooter1.getSelectedSensorVelocity() / Constants.SHOOTER_OUTPUT_TO_ENCODER_RATIO / Constants.TICKS_PER_ROTATION * 10.0 * 60.0;
       // Velocity is ticks per 100ms
   
  }

public void setShooterRPM(double rpm) {
    // double kF = (shooterA.getMotorOutputPercent() * 1023) / shooterA.getSelectedSensorVelocity();
    // shooterA.config_kF(0, kF);
    // Velocity = ticks per 100ms 
    mtShooter1.set(ControlMode.Velocity, shooterRPMToNativeUnits(rpm));
}

public double shooterRPMToNativeUnits(double rpm) {
  return rpm * Constants.SHOOTER_OUTPUT_TO_ENCODER_RATIO * Constants.TICKS_PER_ROTATION / 10.0 / 60.0;
}


  public void stopShooter() {
    //mtIntake.set(ControlMode.PercentOutput, 0);
    mtShooter1.set(0);
    //mtShooter2.set(0);
  }  
  
  public void ShooterUp() {
    
    slndShooter.set(true);
    //bShooterUp = true;
  }

  public void ShooterDown() {
    slndShooter.set(false);

    //bShooterUp = false;  
  }
/*
 public boolean isShooterUp() {
  if (bShooterUp) {
    return true;
  } else {
    return false;
  }
 }
 

 public void ShooterToggle(){
slndShooter.toggle();


 }

 public void ShooterDefault(){
slndShooter.set(true);

 }
 */
 
 


}
