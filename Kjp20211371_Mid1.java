import java.util.*;

public class Kjp20211371_Mid1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int select; // 사용자의 입력값(1 = 가위, 2 = 바위, 3 = 보)
        int com;    // 컴퓨터의 값
        String res; // 컴퓨터의 값에 따라 "가위", "바위", "보"로 한글로 바꾸기

        while(true){    // 무한반복으로 돌다가 4 입력 시 게임 종료
            System.out.println("========== 가위바위보 게임 ==========");
            System.out.println("1. 가위\n2. 바위\n3. 보\n4. 종료");
            System.out.print("입력 : ");
            select = sc.nextInt();
            if(select==4){
                System.out.println("게임을 종료합니다.");
                break;
            }
            if(select < 1 || select > 4){
                System.out.println("잘못된 입력입니다.");
                continue;
            }

            com = (int)(Math.random()*3+1); // 컴퓨터의 값을 random함수 사용해서 정하기, *범위 : 1~3
            if(com==1) res = "가위";         // 컴퓨터의 값을 한글로 저장
            else if(com==2) res = "바위";
            else res = "보";
            // 승패 판정
            if(select==1 && com==3 || select==2 && com==1 || select==3 && com==2){
                System.out.println("이겼습니다!");
            }
            else if(select==com){
                System.out.println("비겼습니다!");
            }
            else{
                System.out.println("졌습니다!");
            }
            System.out.println("컴퓨터의 선택 : " + res);
        }

        sc.close();
    }
}