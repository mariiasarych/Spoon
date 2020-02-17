/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
//this command enaables the feeder and then the shooter in order to shoot them lemons, aim first
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

import com.ctre.phoenix.motorcontrol.can.*; //Talon FX
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode; //Neutral mode for the Falcons
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import frc.robot.subsystems.TurretSubsystem;
import edu.wpi.first.wpilibj.Timer;



public class ShootingCommand extends CommandBase {
  TalonSRX m_feeder;
  TalonFX m_shooterLeft;
  TalonFX m_shooterRight;
  Timer timer;
  private final TurretSubsystem turret_subsystem;

  
  public ShootingCommand(TurretSubsystem subsystem) {
    turret_subsystem = subsystem;
    addRequirements(turret_subsystem);
   m_feeder = new TalonSRX(5);
   m_shooterLeft = new WPI_TalonFX(1);
   m_shooterRight = new WPI_TalonFX(2);
   m_shooterLeft.setNeutralMode(NeutralMode.Brake);
   m_shooterRight.setNeutralMode(NeutralMode.Brake);
   timer = new Timer();
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
   
    timer.start();//make timer be smth get old code, stephon showed me what to do before
    
    m_shooterLeft.set(TalonFXControlMode.PercentOutput,-0.75);
    m_shooterRight.set(TalonFXControlMode.PercentOutput,0.75);
    //set shooter motor to desired max

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
   if(timer.get() >= 1){
    m_shooterLeft.set(TalonFXControlMode.PercentOutput,-0.75);
    m_shooterRight.set(TalonFXControlMode.PercentOutput,0.75);
    m_feeder.set(ControlMode.PercentOutput, -0.75);}
    System.out.println("time " + timer.get());
    


    //continue setting shooter motors to max
    //set feeder motors to max
  }


  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
    //set all 3 motors to zero
    m_shooterLeft.set(TalonFXControlMode.PercentOutput,0.0);
    m_shooterRight.set(TalonFXControlMode.PercentOutput,0.0);
    m_feeder.set(ControlMode.PercentOutput,0.0);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
   return (timer.get() >= 5);
    
  }

}