/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Subsystem_LED extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  private final Spark mtled = Constants.mtLed;
  

  private final double C_blue = 0.87; //FMS
  private final double C_green = 0.77; //FMS
  private final double C_red = 0.61; //FMS
  private final double C_yellow = 0.69; //FMS
  private final double C_aqua = 0.81;
  private final double C_white = 0.93; //Intake
  private final double C_violet = 0.91; //Violet
  private final double C_black = 0.99; //Off
  private final double C_Ocean_Palette = -0.41; //Off

  /******* color assignments ********
  Blue = blue ball
  Red = red ball
  green = shoot ready
  white = teleop enabled
  yellow = Intake
  aqua = 
  violet = 
  ocean_palette = 
  lava_wave = 
  ******* color assignments ********/

  //boolean theArray[];


  public Subsystem_LED(){
    /*theArray = new boolean[4];
    theArray[1] = false;
    theArray[2] = false;
    theArray[3] = false;
    theArray[4] = false;
*/
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setBlue(){
    mtled.set(C_blue);
    SmartDashboard.putString("LED Color", "Blue");
    //SuppliedValueWidget colorWidget = Shuffleboard.getTab("SmartDashboard").addBoolean("FMSColor", () -> true);
    //colorWidget.withProperties(Map.of("colorWhenTrue", Color.blue));

    //theArray[1] = true;
    //theArray[2] = false;
    //theArray[3] = false;
    //theArray[4] = false;
    //SmartDashboard.putBooleanArray("FMS Values", theArray);
  }

  public void setOcean(){
    mtled.set(C_Ocean_Palette);
    //SuppliedValueWidget colorWidget = Shuffleboard.getTab("SmartDashboard").addBoolean("FMSColor", () -> true);
    //colorWidget.withProperties(Map.of("colorWhenTrue", Color.blue));

    //theArray[1] = true;
    //theArray[2] = false;
    //theArray[3] = false;
    //theArray[4] = false;
    //SmartDashboard.putBooleanArray("FMS Values", theArray);
  }

  public void setGreen(){
    mtled.set(C_green);
    //theArray[1] = false;
    //theArray[2] = true;
    //theArray[3] = false;
    //theArray[4] = false;
    //SmartDashboard.putBooleanArray("FMS Values", theArray);
  }
  public void setLavaWave(){
    mtled.set(-0.39);
    //theArray[1] = false;
    //theArray[2] = true;
    //theArray[3] = false;
    //theArray[4] = false;
    //SmartDashboard.putBooleanArray("FMS Values", theArray);
  }
  
  
  public void setRed(){
    mtled.set(C_red);
    //theArray[1] = false;
    //theArray[2] = false;
    //theArray[3] = true;
    //theArray[4] = false;
    //SmartDashboard.putBooleanArray("FMS Values", theArray);
  }

  public void setYellow(){
    mtled.set(C_yellow);
    //theArray[1] = false;
    //theArray[2] = false;
    //theArray[3] = false;
    //theArray[4] = true;
    //SmartDashboard.putBooleanArray("FMS Values", theArray);
    
  }

  public void setAqua(){
    mtled.set(C_aqua);
    /*theArray[1] = false;
    theArray[2] = false;
    theArray[3] = false;
    theArray[4] = false;
    SmartDashboard.putBooleanArray("FMS Values", theArray);*/
  }

  public void setWhite(){
    mtled.set(C_white);
    /*theArray[1] = false;
    theArray[2] = false;
    theArray[3] = false;
    theArray[4] = false;
    SmartDashboard.putBooleanArray("FMS Values", theArray);*/
  }

  public void setViolet(){
    mtled.set(C_violet);
    /*theArray[1] = false;
    theArray[2] = false;
    theArray[3] = false;
    theArray[4] = false;
    SmartDashboard.putBooleanArray("FMS Values", theArray);*/
  }

  public void setBlack(){
    mtled.set(C_black);
    /*theArray[1] = false;
    theArray[2] = false;
    theArray[3] = false;
    theArray[4] = false;
    SmartDashboard.putBooleanArray("FMS Values", theArray);*/
  }
 
}
