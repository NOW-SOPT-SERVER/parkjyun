package org.example.view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);
    private static final InputView inputView = new InputView();

    public static InputView getInstance() {
        return inputView;
    }

    public byte getMenuNumber() {
        System.out.println("은행 서비스입니다. 다음 메뉴에 해당하는 숫자를 입력하세요");
        System.out.println("1. 입금하기 2. 출금하기 3. 잔액조회 4. 계좌이체 5. 종료");
        while (true) {
            System.out.print("숫자 입력 : ");
            byte menuNumber = scanner.nextByte();
            if (menuNumber >= 1 && menuNumber <= 5) {
                return menuNumber;
            }
            System.out.println("1~5 사이의 숫자를 입력해주세요");
        }
    }

    public String getName() {
        System.out.print("이름를 입력해주세요 : ");
        return scanner.next();
    }

    public String getSenderName() {
        System.out.print("송금인의 이름을 입력해주세요 : ");
        return scanner.next();
    }

    public String getReceiverName() {
        System.out.print("수취인의 이름을 입력해주세요 : ");
        return scanner.next();
    }
    public int getWithDrawAmount() {
        while (true) {
            System.out.print("출금하시려는 금액을 입력해주세요 : ");
            int withdrawAmount = scanner.nextInt();
            if (withdrawAmount > 0) {
                return withdrawAmount;
            }
            System.out.println("0보다 큰 금액을 입력해주세요");
        }
    }

    public int getDepositAmount() {
        while (true) {
            System.out.print("입금하시려는 금액을 입력해주세요 : ");
            int depositAmount = scanner.nextInt();
            if (depositAmount > 0) {
                return depositAmount;
            }
            System.out.println("0보다 큰 금액을 입력해주세요");
        }
    }

    public int getTransferAmount() {
        while (true) {
            System.out.print("송금하시려는 금액의 크기를 입력하세요 : ");
            int transferAmount = scanner.nextInt();
            if (transferAmount > 0) {
                return transferAmount;
            }
            System.out.println("0보다 큰 금액을 입력해주세요");
        }
    }

    public int getAccountNumber() {
        System.out.print("계좌번호를 입력하세요 : ");
        return scanner.nextInt();
    }
}
