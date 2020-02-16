/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Joystick;

public class OI extends SubsystemBase {
  public Joystick joy; //creates joy
  
  public OI() {
    joy = new Joystick(0); //assigns joy to a joystick
  }

  public double getLeftStick(){ //gets the value of the axis, inverted so forward is 1
    return joy.getRawAxis(1);
  }

  public double getLeftTurretAxis(){
    return joy.getRawAxis(3)/2+0.5;
  }

  public double getRightTurretAxis(){
    return joy.getRawAxis(4)/2+0.5;
  }

  public double getRightStick(){ 
    return joy.getRawAxis(5);
  }

  public boolean square(){ //gets event of pressing not state
    return joy.getRawButtonPressed(1);
  }

  public boolean x(){ 
    return joy.getRawButtonPressed(2);
  }

  public boolean circle(){
    return joy.getRawButtonPressed(3);
  }

  public boolean triangle(){ 
    return joy.getRawButtonPressed(4);
  }

  public double l1(){ //reutrs motor speed value from button, for left shooting falcon
    double shooterSpeed;
    if (joy.getRawButton(5)){
        shooterSpeed = 1.0;}
        else {
          shooterSpeed = 0.0;
        }
    return shooterSpeed;
  }

  public double r1(){ 
    double feederSpeed;
    if (joy.getRawButton(6)){
      feederSpeed = 1.0;}
      else {
        feederSpeed = 0.0;
      }
  return feederSpeed;
  }

  public boolean l2(){ 
    return joy.getRawButtonPressed(7);
  }

  public boolean r2(){ 
    return joy.getRawButtonPressed(8);
  }

  public boolean share(){ 
    return joy.getRawButtonPressed(9);
  }

  public boolean options(){
    return joy.getRawButtonPressed(10);
  }

  public boolean leftStick(){ 
    return joy.getRawButtonPressed(11);
  }

  public boolean rightStick(){
    return joy.getRawButtonPressed(12);
  }

  public boolean pspsps(){ 
    return joy.getRawButtonPressed(13);
  }

  public boolean bigButtonBoi(){
    return joy.getRawButtonPressed(14);
  }
}
