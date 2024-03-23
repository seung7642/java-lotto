package view;

import domain.LottoNumber;
import domain.LottoNumbers;

import java.util.*;

public class InputView {

    private static final String LOTTO_SPLIT_SEPARATOR = ",";
    private static final String LOTTO_FORMAT_REGEX = String.format("^\\d+(%s\\s*\\d+){5}$", LOTTO_SPLIT_SEPARATOR);

    private InputView() {
    }

    public static int promptForPurchasePrice() {
        System.out.println("구입금액을 입력해 주세요.");
        try {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException("잘못된 값을 입력하였습니다.");
        }
    }

    public static LottoNumbers promptForWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        Scanner scanner = new Scanner(System.in);
        return convertWinningNumbers(scanner.nextLine());
    }

    private static LottoNumbers convertWinningNumbers(String input) {
        assertionValidFormat(input);
        Set<LottoNumber> result = new HashSet<>();
        for (String s : input.trim().split(String.format("\\s*%s\\s*", LOTTO_SPLIT_SEPARATOR))) {
            result.add(new LottoNumber(Integer.parseInt(s)));
        }
        return new LottoNumbers(result);
    }

    private static void assertionValidFormat(String input) {
        if (!input.matches(LOTTO_FORMAT_REGEX)) {
            throw new IllegalArgumentException("잘못된 값을 입력하였습니다.");
        }
    }

    public static LottoNumber promptForBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        Scanner scanner = new Scanner(System.in);
        return new LottoNumber(scanner.nextInt());
    }
}
