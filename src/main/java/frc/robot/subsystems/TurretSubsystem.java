/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;

/**
 * Add your docs here.
 */
public class TurretSubsystem extends SubsystemBase {
  TalonSRX m_turret;
  TalonSRX m_feeder;
  TalonFX m_shooterLeft;
  TalonFX m_shooterRight;
  
  public TurretSubsystem(){
    m_turret = new TalonSRX(6);
    m_feeder = new TalonSRX(5); //pigeon in drive subsystem
    m_shooterLeft = new TalonFX(7);
    m_shooterRight = new TalonFX(8);
    m_shooterLeft.setNeutralMode(NeutralMode.Brake);
    m_shooterRight.setNeutralMode(NeutralMode.Brake);
  }

  public void setTurretSpeed(double speed){ //sets and regulates turret speed
    if (speed > 1) {
      speed = 1;
    }
    else if (speed < -1) {
      speed = -1;
    }
    m_turret.set(ControlMode.PercentOutput, speed*0.25); 
  }

  public double encoderVal(){ //gets the value of the turret magnetic encoder
    double position = m_turret.getSelectedSensorPosition(0);
    System.out.println("encoder position " + position);
    return position;
  }

  public void shooter(double speed){
    if (speed == 0.0){
      m_shooterLeft.set(TalonFXControlMode.PercentOutput,0.0);
      m_shooterRight.set(TalonFXControlMode.PercentOutput,0.0);
    }
    else{
      m_shooterLeft.set(TalonFXControlMode.PercentOutput, -speed*0.5);
      m_shooterRight.set(TalonFXControlMode.PercentOutput, speed*0.5);
    }
  }

   public void feeder(double speed){
      m_feeder.set(ControlMode.PercentOutput,speed);
    }

}
