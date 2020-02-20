
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.EncoderType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class EncoderSubsystem extends SubsystemBase {
    private static final int deviceID1 = 4;
    CANSparkMax m_main;
    CANEncoder encoder;
    int countPerRev;
public EncoderSubsystem(){
    //CANEncoder encoder = new CANEncoder(4);
    m_main = new CANSparkMax(deviceID1, MotorType.kBrushless);
    encoder = new CANEncoder(m_main, EncoderType.kHallSensor, countPerRev);
}
public double getPosition(){
    return (double)encoder.getPosition();
}
public double getVelocity(){
    return (double)encoder.getVelocity();
}
/*
map the input to -180 to 180:

angle = ((((encoder_val - zero_offset) % 4096) + 4096) % 4096 - 2048) * 45 / 512

encoder_val is the raw number back from the encoder (works whether continuous is true or not.)
zero_offset is your -268.

The first modulo (%) pulls into the range -4095…4095, the add and next modulo make it 0…4095, subtracting 2048 makes it -4096…4095, and the multiplication and division scale it to 180.

*/
public double convertAngle(double rotation){
    double angle;//Output variable
    double rot = rotation;//Current value
    double max = 6000;//Maximum value of the encoder
    angle = ((rot % max)+max);
    return angle;
}


}