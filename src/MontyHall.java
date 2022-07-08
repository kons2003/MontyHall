import java.text.DecimalFormat;
import java.util.Scanner;

public class MontyHall {
    public static void main(String[] args) {
        boolean flag = true; // 최상위 while문 조건식
        int num1 = 0, num2 = 0; // 사용자의 선택을 저장할 변수
        int menu = 0; // 직접하기, 확률계산 선택하는 switch문 조건식을 저장할 변수
        int op = 0; // 사회자가 고개하는 문 번호를 저장할 변수
        int j = 0; // 임의의 값을 얻어서 저장할 변수(문 안 결과를 섞을 떼 시용)
        String temp = " "; // 두 값을 바꾸는 데 사용할 임시변수(문 안 결과를 섞을 떼 시용)
        String str = " "; // 선택을 바꿀지 고른 후 결과를 보여주는 switch문 조건식을 저장할 변수
        String exe = " ";
        String carExe = " ";
        String goatExe = " ";
        String[] door = {"O", "X", "X"}; // 문 안에 존재하는 결과

        Scanner scanner = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("###,###");

        System.out.printf("몬티홀의 역설에 따르면, 사회자가 염소가 있는 문을 공개한 후에 선택을 바꾸면%n" +
                "자동차가 나올 확률이 2/3, 염소가 나올 확률이 1/3이라고 한다.%n" +
                "자동차는 O, 염소는 X로 해서 몬티홀의 역설을 실험할 것이다.%n%n");

        while (flag) {
            int car = 0; // O
            int goat = 0; // X
            double cPer = 0.0; // O가 나온 확률
            double gPer = 0.0; // X가 나온 확률
            boolean opFlag = true; // 사용자가 처음 문을 고르는 while문 조건식

            System.out.println("\t<몬티홀의 역설>");
            System.out.println("1.직접하기 2.확률계산");
            System.out.print("원하는 방식(1~2)을 선택하세요. (종료:0)>");

            menu = scanner.nextInt();
            System.out.println();

            switch (menu) {
                case 0: // 프로그램 종료
                    System.out.println("프로그램을 종료합니다.");
                    flag = false;
                    break;
                case 1: // 직접하기
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
                        System.out.println();

                        while (true) {
                            op = (int) (Math.random() * door.length); // 사회자가 공개하는 문 번호 (op = 0~2)
                            if (0 < num1 + 1 && num1 + 1 <= door.length && op != num1 && !door[op].equals("O")) { // 사용자의 선택과 "O"를 제외한 문 하나의 결과를 보여줌
                                System.out.println("당신은 " + (num1 + 1) + "번 문을 고르셨습니다.");
                                System.out.println("사회자가 X가 있는 문 중 하나를 공개합니다.");
                                System.out.printf("%d번 문은 %s입니다.%n", op + 1, door[op]);
                                opFlag = false;
                                break;
                            } else if (0 < num1 + 1 && num1 + 1 <= door.length && (op == num1 || door[op].equals("O"))) {
                                continue;
                            } else { // while문 처음으로 돌아가 문을 다시 고름
                                System.out.println("잘못된 번호를 입력하셨습니다.");
                                break;
                            }
                        }
                        System.out.println();
                    }


                    while (true) { // 사용자가 선택을 바꿀지 결정함
                        System.out.print("선택을 바꾸시겠습니까? (y/n)>");
                        str = scanner.next();

                        if (str.equals("y")) {
                            if (door[num1].equals("O"))
                                System.out.printf("축하합니다. %d번 문에는 %s가 있었습니다.%n", num1 + 1, door[num1]);
                            else
                                System.out.printf("아쉽네요. %d번 문에는 %s가 있었습니다.%n", num1 + 1, door[num1]);
                            break;
                        } else if (str.equals("n")) { // 사회자가 공개한 문과 사용자가 선택한 문을 제외한 문을 공개함
                            for (int i = 0; i < door.length; i++) {
                                if (i != num1 && i != op) {
                                    if (door[i].equals("O"))
                                        System.out.printf("축하합니다. %d번 문에는 %s가 있었습니다.%n", i + 1, door[i]);
                                    else
                                        System.out.printf("아쉽네요. %d번 문에는 %s가 있었습니다.%n", i + 1, door[i]);
                                }
                            }
                            break;
                        } else {
                            System.out.println("y와 n 중 하나를 입력해주세요.");
                            System.out.println();
                        }
                    }
                    System.out.println();

                    break; // case 1(직접하기)의 break
                case 2: // 확률계산
                    int com = 0; // 컴퓨터가 임의로 선택한 값

                    System.out.println("바꿨을 경우, O와 X가 나온 횟수와 둘이 나온 확률을 구합니다.(소수점 7번째 자리 이하는 버려짐.)");
                    System.out.print("몇 번 실행하시겠습니까?>");

                    num2 = scanner.nextInt();
                    System.out.println();

                    for (int k = 0; k < num2; k++) { // 사용자가 입력한 실행 횟수만큼 반복함
                        for (int i = 0; i < door.length; i++) { // 문 안의 결과를 랜덤으로 섞음
                            j = (int) (Math.random() * door.length);
                            temp = door[i];
                            door[i] = door[j];
                            door[j] = temp;
                        }

                        com = (int) (Math.random() * door.length); // com = 0~2

                        while (true) {
                            op = (int) (Math.random() * door.length); // 사회자가 공개하는 문 번호 (op = 0~2)

                            if (op != com && !door[op].equals("O")) {
                                break;
                            } else if (op == com || door[op].equals("O")) {
                                continue;
                            }
                        }

                        for (int i = 0; i < door.length; i++) { // 선택을 바꿈
                            if (i != com && i != op) {
                                if (door[i].equals("O")) {
                                    car += 1; // 바꾼 선택이 O일시 car에 +1함
                                } else {
                                    goat += 1; // 바꾼 선택이 X일시 goat에 +1함
                                }
                            }
                        }
                    } // 사용자가 입력한 반복횟수만큼 수행후 종료

                    exe = df.format(num2);
                    carExe = df.format(car);
                    goatExe = df.format(goat);

                    cPer = ((double) car / (double) num2) * 100;
                    gPer = ((double) goat / (double) num2) * 100;

                    System.out.println("실행 횟수: " + exe + "번");
                    System.out.printf("O가 나온 횟수: %s번\t X가 나온 횟수: %s번%n", carExe, goatExe);
                    System.out.printf("O가 나온 확률: %f%c  X가 나온 확률: %f%c%n", cPer, '%', gPer, '%');
                    System.out.println();

                    break; // case 2(확률계산)의 break
                default:
                    System.out.println("잘못된 번호를 입력하셨습니다.");
                    System.out.println();
            }
        }
    }
}
