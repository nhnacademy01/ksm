package day3hw;

import java.util.Random;

public class CardGame {
    public static void main(String[] args) {
        Game newGame = new Game();

        Card[] userACards = new Card[2];
        Card[] userBCards = new Card[2];

        newGame.setCards(userACards);
        newGame.setCards(userBCards);

        System.out.print("userA 가 최초 선택한 2장의 카드 : ");
        Card.printCard(userACards[0]);
        Card.printCard(userACards[1]);
        System.out.println();
        System.out.print("userB 가 최초 선택한 2장의 카드 : ");
        Card.printCard(userBCards[0]);
        Card.printCard(userBCards[1]);
        System.out.println();
        System.out.println("--------------------------------");

        newGame.whoIsWinner(userACards, userBCards);
    }
}

class Game {
    int[] checkOverlapSuits = new int[4];
    int[] checkOverlapNumbers = new int[4];

    public void setCards(Card[] userCards) {
        Random random = new Random();
        for (int i = 0; i < userCards.length; i++) {
            Suit randomSuit = new Suit(random.nextInt(5) + 1);
            Number randomNumber = new Number(random.nextInt(13) + 1);
            //중복체크
            if (!checkOverlapCard(randomSuit.order, randomNumber.value)) {
                i--;
                continue;
            }
            if (randomSuit.order == 5) {
                randomNumber = new Number(0);
            }
            userCards[i] = new Card(randomSuit, randomNumber);
        }
    }

    private void setCheckOverlap(int checkSuit, int checkNumber) {
        for (int i = 0; i < this.checkOverlapSuits.length; i++) {
            if (this.checkOverlapSuits[i] == 0) {
                this.checkOverlapSuits[i] = checkSuit;
            }
            if (this.checkOverlapNumbers[i] == 0) {
                this.checkOverlapNumbers[i] = checkNumber;
            }
        }
    }

    private boolean checkOverlapCard(int checkSuit, int checkNumber) {
        for (int i = 0; i < this.checkOverlapSuits.length; i++) {
            if (this.checkOverlapSuits[i] == checkSuit && this.checkOverlapNumbers[i] == checkNumber) {
                //중복이면
                return false;
            }
        }
        //중복된게 없으면
        setCheckOverlap(checkSuit, checkNumber);
        return true;
    }

    public void whoIsWinner(Card[] userACards, Card[] userBCards) {
        // 사용자가 뽑은 두장의 카드중 더 가치가 높은 카드 선택
        Card userACard = selectOne(userACards);
        Card userBCard = selectOne(userBCards);

        System.out.print("userA 가 꺼낸 1장의 카드 : ");
        Card.printCard(userACard);
        System.out.println();
        System.out.print("userB 가 꺼낸 1장의 카드 : ");
        Card.printCard(userBCard);
        System.out.println();
        System.out.println("--------------------------------");

        Card[] usersCard = {userACard, userBCard};
        Card winner = selectOne(usersCard);

        if (userACard.suit.order == 5 && userBCard.suit.order == 5) {
            System.out.println("> 무승부 입니다!");
        } else if (winner.number.value == userACard.number.value && winner.suit.order == userACard.suit.order) {
            System.out.println("> userA가 승리하였습니다!");
        } else {
            System.out.println("> userB가 승리하였습니다!");
        }
    }

    public Card selectOne(Card[] userCards) {
        if (userCards[0].suit.order == 5 || userCards[1].suit.order == 5) {
            //조커카드 선택
            if (userCards[0].suit.order == 5) {
                return userCards[0];
            } else {
                return userCards[1];
            }
        }
        if (userCards[0].number.value > userCards[1].number.value) {
            // 더 큰 숫자 카드 선택
            return userCards[0];
        } else if (userCards[0].number.value == userCards[1].number.value) {
            // 두 카드의 숫자가 같을때는 spades~clubs 의 가중치(order)을 비교해서 더 큰값 카드 선택
            if (userCards[0].suit.order > userCards[1].suit.order) {
                return userCards[0];
            } else {
                return userCards[1];
            }
        }
        return userCards[1];
    }
}

class Suit {
    String display;
    int order;

    public Suit(int randomSuit) {
        order = randomSuit;
        switch (order) {
            case 1:
                display = "spades";
                break;
            case 2:
                display = "hearts";
                break;
            case 3:
                display = "diamonds";
                break;
            case 4:
                display = "clubs";
                break;
            case 5:
                display = "joker";
                break;
            default:
                display = "오류";
        }
    }
}

class Number {
    String numberDisplay;
    int value;

    public Number(int randomValue) {
        value = randomValue;
        switch (value) {
            case 0:
                numberDisplay = "Jocker";
                break;
            case 1:
                numberDisplay = "Ace";
                break;
            case 11:
                numberDisplay = "Jack";
                break;
            case 12:
                numberDisplay = "Queen";
                break;
            case 13:
                numberDisplay = "King";
                break;
            default:
                numberDisplay = Integer.toString(randomValue);
        }
    }
}

class Card {
    public Suit suit;
    public Number number;

    public Card(Suit suit, Number number) {
        this.suit = suit;
        this.number = number;
    }

    public static void printCard(Card selectCard) {
        System.out.print("[" + selectCard.suit.display + "_" + selectCard.number.numberDisplay + "] ");
    }
}