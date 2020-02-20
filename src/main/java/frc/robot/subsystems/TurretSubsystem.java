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
  private static final int deviceID1 = 10;
  private static final int deviceID2 = 6;
  private static final int deviceID3 = 7;
  private static final int deviceID4 = 8;
  TalonSRX m_turret;
  TalonSRX m_feeder;
  TalonFX m_shooterLeft;
  TalonFX m_shooterRight;
  
  public TurretSubsystem(){
    m_feeder = new TalonSRX(deviceID1); //pigeon in drive subsystem
    m_turret = new TalonSRX(deviceID2); //magnetic encoder here
    m_shooterLeft = new TalonFX(deviceID3);
    m_shooterRight = new TalonFX(deviceID4);
    boolean _brake = true;
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
    // System.out.println("encoder position " + position);
    return position;
    //Counter clockwise from hopper is -10000
    //Clockwise from hopper is 10000
    //Limit set at ~8000
  }
  public void encoderReset(boolean button){
    if (button==true){
    m_turret.setSelectedSensorPosition(0);
    }else{
      
    }
  }
  public void shooter(double speed){
    if (speed == 0.0){
      m_shooterLeft.set(TalonFXControlMode.PercentOutput,0.0);
      m_shooterRight.set(TalonFXControlMode.PercentOutput,0.0);
    }
    else{
      m_shooterLeft.set(TalonFXControlMode.PercentOutput, -speed);
      m_shooterRight.set(TalonFXControlMode.PercentOutput, speed);
    }
  }

<<<<<<< HEAD
  
}
=======
  public void shooterEncoder(){
    m_shooterLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    m_shooterRight.getSelectedSensorVelocity();
  }

   public void feeder(double speed){
      m_feeder.set(ControlMode.PercentOutput,speed);
    }

}
>>>>>>> 4d67253965527a8916527825bedb9e00cb805566
