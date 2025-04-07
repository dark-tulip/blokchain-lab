import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import services.SenderServiceImpl;
import services.SendingConfirmationService;
import services.SendingConfirmationServiceImpl;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SendingConfirmationServiceImplTest {

  static SendingConfirmationService confirmationService;

  private static final String RUN_FOR_MODEL_JSON_PATH = "src/test/resources/results_backwards2.json";
  private static final String OUTPUT_PATH             = "src/test/resources/runTest2.log";

  public SendingConfirmationServiceImplTest() {

  }

  @BeforeAll
  static void init() {
    confirmationService = new SendingConfirmationServiceImpl();
  }

  @Test
  public void sendCustomIdTest() {
    boolean consensusReached = confirmationService.sendForConfirmationCustomId(new SenderServiceImplForTest(),
        RUN_FOR_MODEL_JSON_PATH,
        0);

    assertTrue(consensusReached);
  }

  @Test
  public void sendCustomValuesTest() {
    boolean consensusReached = confirmationService.sendForConfirmationCustomValues(new SenderServiceImplForTest(),
        RUN_FOR_MODEL_JSON_PATH,
        1.0,
        3.0919090686339907);

    assertTrue(consensusReached);
  }

  @Test
  public void sendMinMessagesTest() {
    boolean consensusReached = confirmationService.sendForConfirmationMinMessages(new SenderServiceImplForTest(),
        RUN_FOR_MODEL_JSON_PATH);

    assertTrue(consensusReached);
  }

  @Test
  public void sendMaxProbabilityTest() {
    boolean consensusReached = confirmationService.sendForConfirmationMaxProbability(new SenderServiceImplForTest(),
        RUN_FOR_MODEL_JSON_PATH);

    assertTrue(consensusReached);
  }

  static class SenderServiceImplForTest extends SenderServiceImpl {
    @Override
    public String getLogPath() {
      return OUTPUT_PATH;
    }
  }
}
