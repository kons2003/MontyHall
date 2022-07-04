import java.util.Scanner;

public class MontyHall {
    public static void main(String[] args) {
        boolean flag = true; // 최상위 while문 조건식
        boolean opFlag = true; // 사용자가 처음 문을 고르는 while문 조건식
        int num1 = 0, num2 = 0; // 사용자의 선택을 저장할 변수
        int menu = 0; // 직접하기, 확률계산 선택하는 switch문 조건식을 저장할 변수
        int op = 0; // 사회자가 고개하는 문 번호를 저장할 변수
        String str = " "; // 선택을 바꿀지 고른 후 결과를 보여주는 switch문 조건식을 저장할 변수
        String[] door = {"O", "X", "X"}; // 문 안에 존재하는 결과

        Scanner scanner = new Scanner(System.in);
        while (flag) {
            System.out.println("\t<몬티홀의 역설>");
            System.out.println("1.직접하기 2.확률계산");
            System.out.print("원하는 방식(1~2)을 선택하세요. (종료:0)>");
            menu = scanner.nextInt();

            switch (menu) {
                case 0: // 프로그램 종료
                    System.out.println("프로그램을 종료합니다.");
                    flag = false;
                    break;
                case 1: // 직접하기
                    String temp = " "; // 두 값을 바꾸는 데 사용할 임시변수
                    int j = 0; // 임의의 값을 얻어서 저장할 변수

                    for (int i = 0; i < door.length; i++) { // 문 안의 결과를 랜덤으로 섞음
                        j = (int) (Math.random() * door.length);
                        temp = door[i];
                        door[i] = door[j];
                        door[j] = temp;
                    }


                    while (opFlag) { // 사용자가 문을 고름
                        System.out.println("[1] [2] [3]");
                        System.out.print("1~3번 문 중에서 하나를 고르세요.>");
                        num1 = (scanner.nextInt() - 1);

                        while (true) {
                            op = (int) (Math.random() * 3); // 사회자가 공개하는 문 번호
                            if (!(door[op].equals(door[num1])) && !(door[op].equals("O"))) { // 사용자의 선택과 "O"를 제외한 문 하나의 결과를 보여줌
                                System.out.println("당신은 " + (num1 + 1) + "번 문을 고르셨습니다.");
                                System.out.println("사회자가 X가 있는 문 중 하나를 공개합니다.");
                                System.out.printf("%d번 문은 X입니다.%n", op + 1);
                                opFlag = false;
                                break;
                            } else if (door[op].equals(door[num1]) || door[op].equals("O")) {
                                continue;
                            } else { // while문 처음으로 돌아가 문을 다시 고름
                                System.out.println("잘못된 번호를 입력하셨습니다.");
                                break;
                            }
                        }
                    }

                    System.out.print("선택을 바꾸시겠습니까? (y/n)>");
                    str = scanner.next();

                    while (true) {
                        if (str.equals("y")) {
                            System.out.printf("%d번 문에는 %s가 있었습니다.%n", num1 + 1, door[num1]);
                            break;
                        } else if (str.equals("n")) {
                            for (int i = 0; i < door.length; i++) {
                                if (!door[i].equals(door[num1]) && !door[i].equals(door[op])) {
                                    System.out.printf("%d번 문에는 %s가 있었습니다.%n", i + 1, door[i]);
                                }
                            }
                            break;
                        } else {
                            System.out.println("y와 n 중 하나를 입력해주세요.");
                        }
                    }

                    break; // case 1(직접하기)의 break
                case 2: // 확률계산
                    num2 = scanner.nextInt();
                    break;
                default:
                    System.out.println("잘못된 번호를 입력하셨습니다.");
            }
        }
    }
}
