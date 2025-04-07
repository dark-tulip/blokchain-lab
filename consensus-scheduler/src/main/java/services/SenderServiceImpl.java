package services;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class SenderServiceImpl implements SenderService {

    // Заданные вероятности для организаций
    private final Map<String, Double> orgProbabilities = Map.of(
        "Org1", 0.995,
        "Org2", 0.998,
        "Org3", 0.996,
        "Org4", 0.999
    );

    /**
     * симулирует реакцию организации, (подтверждает транзакцию с заданной вероятностью)
     */
    @Override
    public int getReply(String orgName, int transactionId) {
        double probability = orgProbabilities.getOrDefault(orgName, 0.0);
        double randomValue = ThreadLocalRandom.current().nextDouble(); // от 0.0 до 1.0
        return randomValue < probability ? 1 : 0;
    }

    @Override
    public int getMaxRequestNum() {
        return 3; // не более 3 раз к одной организации
    }

    @Override
    public int getMaxRequestTotalNum() {
        return 10; // максимум 10 запросов всего
    }

    @Override
    public long getTimeoutSec() {
        return 10;
    }

    @Override
    public long getWaitingTimeSec() {
        return 1; // wait 1 секунду перед повторной отправкой
    }

    @Override
    public String getLogPath() {
        return "logs/scheduler-log.txt";
    }
}
