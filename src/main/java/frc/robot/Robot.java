/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
//Commands & Subsystems
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.*;
//Individual imports



public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
  DriveSubsystem drive_subsystem;
  CameraSubsystem camera_subsystem;
  EncoderSubsystem encoder_subsystem;
  OI oi;
  double turretVal;
  double turretVal2;
  TurretSubsystem turret_subsystem;

  
 

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    drive_subsystem = new DriveSubsystem();
    camera_subsystem = new CameraSubsystem();
    encoder_subsystem = new EncoderSubsystem();
    turret_subsystem = new TurretSubsystem();
    oi = new OI();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    double leftAdjust = -1.0; 
    double rightAdjust = -1.0; // default speed values for chase
    double mindistance = 5;
    leftAdjust -= aimbot();
    rightAdjust += aimbot();
     if(Math.abs(camera_subsystem.getTy()) <= mindistance){
       drive_subsystem.tankDrive(0, 0, 1);
     }else{
       if(camera_subsystem.isTarget() == false){
         drive_subsystem.tankDrive(-.5, .5, .5);
       }else if((camera_subsystem.isTarget() == true)){
         drive_subsystem.tankDrive(leftAdjust, rightAdjust, 1);
         }
     }
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    drive_subsystem.tankDrive(oi.getLeftStick(), oi.getRightStick(), 1);
    drive_subsystem.getYaw();
    drive_subsystem.tankDrive(oi.getLeftStick(), oi.getRightStick(),1);
    print("Encoder position is"+encoder_subsystem.getPosition());
    print("Encoder velocity is"+encoder_subsystem.getVelocity());
    turretVal = oi.getLeftTurretAxis();
    turretVal2 = oi.getRightTurretAxis();
    turretVal2 = turretVal - turretVal2;
    turret_subsystem.setTurretSpeed(turretVal2);
  
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
  //Other functions
  public double aimbot() {
    float kp = -.05f;
    float minCommand = .005f;
    float steeringAdjust = 0.05f;
    //double txEntry = getValue("tx").getDouble(0.0);
    float tx = (float)camera_subsystem.getTx();
    //SmartDashboard.setDefaultNumber("TX", tx);
    float headingError = -tx;

    if(tx > 1) {
        steeringAdjust = kp*headingError -minCommand;
    }else if (tx < 1){
        steeringAdjust = kp*headingError + minCommand;
    }
    return steeringAdjust;
  }
  public void print(String value){
    System.out.println(value);
  }
}
