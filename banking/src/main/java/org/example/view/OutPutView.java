package org.example.view;

public class OutPutView {

    private static final OutPutView outPutView = new OutPutView();

    public static OutPutView getInstance() {
        return outPutView;
    }

    public void stopService() {
        System.out.println("은행 서비스를 종료합니다");
    }

    public void printBalanceMessage(int balance) {
        System.out.println("현재 고객님의 잔액은 " + balance + "원입니다");
    }

    public void printWithDrawMessage(int withdrawAmount, int balance) {
        System.out.println(withdrawAmount + "원을 출금하겠습니다");
        System.out.println("출금 후 고객님의 잔액은 " + balance + "원입니다");
    }

    public void printDepositMessage(int depositAmount, int balance) {
        System.out.println(depositAmount + "원을 입금하겠습니다");
        System.out.println("입금 후 고객님의 잔액은 " + balance + "원입니다");
    }

    public void printTransferMessage(int transferAmount, int balance) {
        System.out.println(transferAmount + "원 송금에 성공하였습니다.");
        System.out.println("송금 이후 고객님의 잔액은 " + balance + "원입니다");
    }

    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}

