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
    m_turret = new WPI_TalonSRX(6);
    encoder = new AnalogInput(1); //not the correct port
  }

  public void setMaxSpeed () {
    m_turret.set(.20);
  }
  
  public void setMinSpeed () {
    m_turret.set(-.20);
  }

  public void setTurretSpeed (double t_speed) {
    //here must be the formula that would make joystick values go from -.20 to .20
    if (t_speed > 1){
      t_speed = 1;
    }
    else if (t_speed < -1){
      t_speed = - 1;
    }
    t_speed = t_speed *.20;
    m_turret.set (t_speed);
  }

  public void resetTurretEncoder () { //probably should be in the encoder subsystem
    encoder.resetAccumulator();
  }

  public double getCurrentAngle (TurretSubsystem turret_subsystem){
    //here must be a formula that will ensure that encoder's values are accurate
    currentAngle = turret_subsystem.encoder.getVoltage();
    return currentAngle;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
