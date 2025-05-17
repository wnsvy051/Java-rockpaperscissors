import java.util.*;

public class Kjp20211371_Mid2 {
    // 전역변수
    static int wins = 0; // 승률 계산을 위한 승리 카운팅
    static int total = 0; // 승률 계산을 위한 판수 카운팅
    static int comMoney = 5000; // 컴퓨터의 초기 자금
    static int myMoney; // 유저의 초기 자금
    static int betMoney; // 베팅 금액

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 게임 시작
        while(true){
            System.out.println("========== 가위바위보 게임 ==========");
            System.out.print("초기 자금 입력: ");
            myMoney = sc.nextInt();
            if(myMoney <= 0){ // 예외 처리
                System.out.println("1원 이상 입력하세요.\n");
                continue;
            }
            break;
        }

        while(true) {
            // 현재 상태 출력
            System.out.println("\n[현재 상태]");
            System.out.println("내 자금: " + myMoney + "원");
            System.out.println("컴퓨터 자금: " + comMoney + "원");

            // 컴퓨터 선택
            int com = getComChoice();
            //System.out.println(com);    // 테스트용 컴퓨터의 선택 출력

            // 가위바위보 선택
            int user = getUserChoice(sc);
            if(user == 4) break; // 4번 선택 시 게임 종료

            // 배팅 금액 입력
            setBetting(sc);

            // 승패 판정
            String result = judgeResult(com, user);

            // 결과 출력
            printResult(com, user, result);

            // 유저 또는 컴퓨터의 자금이 0원이 될 경우 게임 종료
            if(myMoney <= 0 || comMoney <= 0) {
                System.out.println("\n게임 종료\n자금이 모두 소진되었습니다.");
                break;
            }
        }

        // 최종 결과
        System.out.println("\n==============================");
        System.out.println("최종 승률: " + getWinRate() + "%");
        System.out.println("최종 금액: " + myMoney + "원");
        System.out.println("게임을 종료합니다.");
        sc.close();
    }

    // 컴퓨터 선택
    public static int getComChoice(){
        return (int) ((Math.random() * 3) + 1); // 랜덤 함수 이용
    }

    // 유저 선택
    public static int getUserChoice(Scanner sc){
        int choice;
        while(true){
            System.out.println("\n[가위바위보]");
            System.out.print("가위(1), 바위(2), 보(3), 종료(4): ");
            choice = sc.nextInt();
            if (choice < 1 || choice > 4) { // 예외 처리
                System.out.println("1~4 사이의 값을 입력하세요.");
                continue;
            }
            break;
        }
        return choice;
    }

    // 배팅
    public static void setBetting(Scanner sc){

        while(true) {
            System.out.print("\n배팅 금액 입력: ");
            betMoney = sc.nextInt();

            if(betMoney <= 0) { // 예외처리
                System.out.println("0원 이하는 배팅할 수 없습니다.");
                continue;
            }
            // 현재 자금보다 배팅 자금을 높게 설정할 수 없음
            if(betMoney > myMoney) {
                System.out.println("자금이 부족합니다.");
                System.out.println("현재 자금 : " + myMoney + "원");
                continue;
            }

            if(betMoney > comMoney) {
                System.out.println("컴퓨터의 자금이 부족합니다.");
                System.out.println("컴퓨터의 자금 : " + comMoney + "원");
                continue;
            }
            break;
        }
    }

    // 승패 판정
    public static String judgeResult(int com, int user){
        total++; // 승률 계산을 위해 총 게임 수 증가

        if(user == com) return "draw"; // 같을 경우 비김
        // 이기는 경우
        if(user == 1 && com == 3 || user == 2 && com == 1 || user == 3 && com == 2){
            wins++; // 승리 수 카운트
            myMoney += betMoney;
            comMoney -= betMoney;
            return "win";
        }
        else { // 지는 경우
            myMoney -= betMoney;
            comMoney += betMoney;
            return "lose";
        }
    }

    // 결과 출력
    public static void printResult(int com, int user, String result){
        String[] choices = {"가위", "바위", "보"};
        // 현재 choices는 0=가위 1=바위 2=보, 메인에서 넘어오는 값(인덱스)은 +1 되어있으므로 -1 작업 필요함
        System.out.println("\n[결과]");
        System.out.println("컴퓨터: " + choices[com-1]);
        System.out.println("당신: " + choices[user-1]);
        System.out.println("==============================");

        switch(result) {
            case "win":
                System.out.println("승리 +" + betMoney + "원");
                break;
            case "lose":
                System.out.println("패배 -" + betMoney + "원");
                break;
            default:
                System.out.println("무승부");
        }
    }

    // 승률 계산
    public static double getWinRate(){
        if (total == 0) { // 게임 안 했을 시
            return 0;
        } else {
            return Math.round((wins * 100.0) / total); // 반올림 계산
        }
    }
}