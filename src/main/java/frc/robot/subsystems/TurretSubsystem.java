/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TurretSubsystem extends SubsystemBase {

  public WPI_TalonSRX m_turret;
  AnalogInput encoder;
  double encoderValue;
  double currentAngle;
  double maxspeed;
  TurretSubsystem m_turretSystem;
  /**
   * Creates a new TurretSubsystem.
   */
  public TurretSubsystem() {
    m_turret = new WPI_TalonSRX(1);
    encoder = new AnalogInput(1);
  }

  // public double maxSpeed (TurretSubsystem turret_subsystem, double speed) {
  //   if (speed > .25){
  //     speed = .25;
  //   }
  //   else if (speed < -.25){
  //     speed = -.25;
  //   }
  //   maxspeed = speed;
  //   return maxspeed;
  // }

  public void setMaxSpeed () {
    m_turret.set(.25);
  }
  
  public void setMinSpeed () {
    m_turret.set(-.25);
  }

  public void setTurretSpeed (double speed) {
    speed = speed * .25;
    m_turret.set (speed);
  }

  public double getEncoderValue () {
    encoderValue = encoder.getVoltage();
    return encoderValue; 
  }

  public double getCurrentAngle (TurretSubsystem turret_subsystem){
    currentAngle = getEncoderValue();
    return currentAngle;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
