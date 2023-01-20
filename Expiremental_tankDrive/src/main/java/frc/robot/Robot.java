// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;
  private Joystick m_leftStick;
  private Joystick m_rightStick;
private final Timer m_timer = new Timer();

  private final WPI_TalonSRX m_leftMotor = new WPI_TalonSRX(0);
  private final WPI_TalonSRX m_rightMotor = new WPI_TalonSRX(1);

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightMotor.setInverted(true);

    m_myRobot = new DifferentialDrive(m_leftMotor, m_rightMotor);
    m_leftStick = new Joystick(0);
    m_rightStick = new Joystick(1);
  }

   /** This function is run once each time the robot enters autonomous mode. */
   @Override
   public void autonomousInit() {
     m_timer.reset();
     m_timer.start();
   }
 
   /** This function is called periodically during autonomous. */
   @Override
   public void autonomousPeriodic() {
     // Drive for 2 seconds
     if (m_timer.get() < 2.0) {
       // Drive forwards half speed, make sure to turn input squaring off
       m_myRobot.tankDrive(0.5, 0.0, false);
     } else {
       m_myRobot.stopMotor(); // stop robot
     }
   }

  @Override
  public void teleopPeriodic() {
    m_myRobot.tankDrive(-m_leftStick.getY(), -m_rightStick.getY());
  }
}
