package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.RobotState;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;
import frc.team2412.robot.subsystems.constants.IndexerConstants;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexIntakeThreeBackCommand extends CommandBase {
	private IndexerSensorSubsystem m_indexerSensorSubsystem;
	private IndexerMotorSubsystem m_indexerMotorSubsystem;

	public IndexIntakeThreeBackCommand(IndexerSensorSubsystem sensorSubsystem, IndexerMotorSubsystem motorSubsystem) {
		m_indexerSensorSubsystem = sensorSubsystem;
		m_indexerMotorSubsystem = motorSubsystem;
		addRequirements(sensorSubsystem, motorSubsystem);
	}

	@Override
	public void execute() {
		if (m_indexerSensorSubsystem.getIntakeBackSensorValue())
			m_indexerMotorSubsystem.setBackMotor(-1);
	}

	@Override
	public void end(boolean cancel) {
		RobotState.m_unbalancedSide = RobotState.UnbalancedSide.BACK;
		m_indexerMotorSubsystem.stopBackPID(IndexerConstants.LONG_STOP_DISTANCE);
		RobotState.m_ballCount++;
	}

	@Override
	public boolean isFinished() {
		return m_indexerSensorSubsystem.getIndexBackMidSensorValue();
	}

}
