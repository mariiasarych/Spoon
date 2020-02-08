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
  public Joystick joy;
  
  public OI() {
    joy = new Joystick(0);
  }

  public double getLeftStick(){
    return -joy.getRawAxis(1);
  }

  public double getRightStick(){
    return -joy.getRawAxis(5);
  }

  public boolean square(){ //gets event of pressing not state
    return joy.getRawButtonPressed(1);
  }

  public boolean x(){ //gets event of pressing not state
    return joy.getRawButtonPressed(2);
  }

  public boolean circle(){ //gets event of pressing not state
    return joy.getRawButtonPressed(3);
  }

  public boolean triangle(){ //gets event of pressing not state
    return joy.getRawButtonPressed(4);
  }

}
