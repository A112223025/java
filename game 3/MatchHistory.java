import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// 儲存匹配記錄的集合
public class MatchHistory {
    private List<String> history;

    // 建構子，初始化歷史記錄清單
    public MatchHistory() {
        this.history = new ArrayList<>();
    }

    // 新增一條匹配記錄
    public void addRecord(String record) {
        String timestampedRecord = "[" + LocalDateTime.now() + "] " + record;
        history.add(timestampedRecord); // 將帶有時間戳的記錄加入清單
        System.out.println("匹配記錄已新增: " + timestampedRecord);
    }

    // 顯示所有匹配記錄
    public void printHistory() {
        System.out.println("匹配歷史記錄:");
        if (history.isEmpty()) {
            System.out.println("目前沒有匹配記錄。");
        } else {
            // 打印表格標題
            System.out.println("-----------------------------------------");
            System.out.printf("| %-4s | %-30s |\n", "序號", "匹配記錄");
            System.out.println("-----------------------------------------");

            // 遍歷歷史記錄，按序號輸出
            for (int i = 0; i < history.size(); i++) {
                System.out.printf("| %-4d | %-30s |\n", i + 1, history.get(i));
            }

            // 打印表格結尾
            System.out.println("-----------------------------------------");
        }
    }

    // 清除所有匹配記錄
    public void clearHistory() {
        history.clear(); // 清空記錄清單
        System.out.println("所有匹配記錄已清除！");
    }

    // 獲取所有匹配記錄（回傳 List）
    public List<String> getHistory() {
        return new ArrayList<>(history); // 回傳歷史記錄的副本
    }
}

