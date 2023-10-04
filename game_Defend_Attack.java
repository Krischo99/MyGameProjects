package GameProject;

import java.util.Random;
import java.util.Scanner;

public class game_Defend_Attack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello stranger. What is your name? ");
        String name = scanner.nextLine();
        System.out.printf("Okay %s are u ready for Battle?\n", name);

        // ТУК СЕ ПРАВИ ПРОВЕРКА ЗА ОТГОВОРА, ЗАЩОТО МОЖЕ ДА Е ВСЯКАКЪВ
        while (true) {
            String answer = scanner.nextLine();

            // ВЪЗМОЖНИТЕ ВАРИАЦИИ НА ОТГОВОРА СА В ТЕЗИ ПРОВЕРКИ
            if (answer.equalsIgnoreCase("yes")) {
                System.out.println("Come it and Let your adventure begin");
                break;
            } else if (answer.equalsIgnoreCase("no")) {
                System.out.printf("GoodBay %s we are so disappointed from your weakness!!!", name);
                return;
            } else {
                System.out.printf("I cannot understand you %s. Please repeat your answer!\n", name);
            }
        }

        // ПРАВИЛА И НАЧИН НА ИГРА
        System.out.printf("1. Everything will happen in stages\n2. First %s you have to choose a difficulty\n", name);
        System.out.println("3. Then every round you have to choose whether to defend or attack as well as the defense rating");
        System.out.println("But be careful, because if your rating is two numbers higher or lower than your opponent, it can be bad for you");
        System.out.println("1. Throughout the adventure, you and your opponent will alternate who gets to choose first. The one who is first " +
                "will be in the \"offensive position\", which means that he will take away from his opponent's life.");
        System.out.println("2. The numbers you can choose from are in the interval from 1-10. If the number during attack or defense exceeds" +
                " that of your opponent by three units, you will take as damage the sum of your number and the opponent's number. The same " +
                "applies to your opponent if you are in the \"offensive position\".\n3. However, if you both choose the same thing (either " +
                "attack or defend) you collide and no one takes any damage");
        System.out.println("Now choose whether you want to be First or Second!");

        //ИЗБОР И НАСТРОЙКИ НА "БОТА" И ИГРАТА
        //ПРОВЕРКА ДАЛИ СИ ИЗБРАЛ МЕЖДУ 1 И 2, КАКТО И ДАЛИ СИ ПЪРВИ ИЛИ ВТОРИ
        byte yourChose = Byte.parseByte(scanner.nextLine());
        boolean firstPick = false;
        while (true) {
            if (yourChose == 1) {
                firstPick = true;
                break;
            } else if (yourChose == 2) {
                break;
            } else {
                System.out.println("Invalid input! Try again");
                yourChose = Byte.parseByte(scanner.nextLine());
            }
        }

        //ПРОМЕНЛИВА КОЯТО ЩЕ СЛЕДИ РУНДОВЕТЕ И ЩЕ ОПРЕДЕЛЯ КОЙ ЩЕ АТАКУВА И В КАКЪВ РЕД
        int countRounds = 1;

        // ТУК СА ЖИВОТЪТ НА ИГРАЧА И ТОЗИ НА КОМПЮТЪРА
        byte healthPC = 100;
        byte yourHealth = 100;

        //СЪЗДАВАНЕ НА "БОТА"
        Random random = new Random();
        int botNumChoose;

        //СЪЩИНСКАТА ИГРА И НЕЙНИЯ ХОД, И ДИНАМИКА
        while (healthPC > 0 && yourHealth > 0) {
            System.out.printf("lets round %d - BEGIN !\n", countRounds);

            //ГЕНЕРИРАНЕ НА ЧИСЛО ОТ БОТА ЗА ДА СЕ ИЗПОЛЗВА ПО- НАДОЛУ В КОДА И РЕСЕТ НА ЧИСЛОТО
            botNumChoose = random.nextInt(10) + 1;

            //ПРОМЕНЛИВА КОЯТО ЩЕ СЛЕДИ ПО КОЛКО ЖИВОТ СЕ ОТНЕМА НА ХОД КАКТО И ПРОМЕНЛИВАТА, КОЯТО ДЪРЖИ КАКВО СИ ИЗБРАЛ ТИ И БОТА
            // ТУК СЕ ИЗВЪРШВА И РЕСЕТВАНЕТО НА ПРОМЕНЛИВИТЕ
            String yourChoose = "";
            String botChoose = "";

            //ПРОВЕРКИ ЗА ДА СЕ ВИДИМ КОГА ОТ НА КОГО ЩЕ СЕ ИЗВАЖДАТ ЖИЗНЕНИ ТОЧКИ
            if (firstPick) {

                //АКО ИЗБЕРЕШ ДА СИ ПЪРВИ ЗНАЧИ СИ С НЕЧЕТНИТЕ ЧИСЛА И ЩЕ Е ТВОЙ ХОД НА ВСЯКО НЕЧЕТНО ЧИСЛО
                if (countRounds % 2 != 0) {
                    System.out.println("Now you are in \"attack position\"");
                    System.out.println("Choose defend or attack. 1 is for attack 2 is for defend");
                    yourChose = Byte.parseByte(scanner.nextLine());

                    //ПРОВЕРКА ДАЛИ НЕ СЕ ОПИТВАШ ДА БЪГНЕШ ИГРАТА С ДРУГИ ИЗБОРИ
                    while (true) {
                        if (yourChose == 1) {
                            yourChoose = "attack";
                            System.out.println("You choose to attack the enemy. You know, the best defense is offense");
                            break;
                        } else if (yourChose == 2) {
                            yourChoose = "defend";
                            System.out.println("You choose to defend yourself. You obviously know that the best offense is defense");
                            break;
                        } else {
                            System.out.println("Invalid input! Try again");
                            yourChose = Byte.parseByte(scanner.nextLine());
                        }
                    }

                    //ИЗБОРА НА БОТА
                    if (botNumChoose <= 5) {
                        System.out.println("Your enemy chose to defend himself");
                        botChoose = "defend";
                    } else {
                        System.out.println("Be careful! The enemy chose to attack you");
                        botChoose = "attack";
                    }

                    //ТВОЯ ИЗБОР НА ЛЕВЕЛ ЗА АТАКА ИЛИ ЗАЩИТА
                    System.out.println("Choose your attack level");
                    yourChose = Byte.parseByte(scanner.nextLine());

                    //СРАВНЯВАНЕ НА РЕЗУЛТАТА
                    if (!yourChoose.equals(botChoose)) {
                        if (botNumChoose < yourChose - 1 || botNumChoose > yourChose + 1) {
                            System.out.println("Your enemy choose "+ botNumChoose);
                            healthPC -= (yourChose + botNumChoose);
                            System.out.printf("Well done! Your enemy take %d damage. Now your enemy has %d health\n", yourChose + botNumChoose, healthPC);
                        } else {
                            System.out.println("Your enemy choose "+ botNumChoose);
                            System.out.println("What an amazing fight, but neither of you managed to hurt the other enough. " +
                                    "Now rest and think better in the next round");
                        }
                    } else {
                        if (botChoose.equalsIgnoreCase("attack")) {
                            System.out.println("You both attacked each other at the same time, but neither managed to " +
                                    "take the other down. Good luck in the next round adventurer");
                        } else {
                            System.out.println("You both chose protection! No one attacks the other. Better luck in the next round");
                        }
                    }
                } else {

                    //ТУК КОДА СЕ ПРЕПОВТАРЯ С ГОРНИЯ, НО ИМА МАЛКИ ПРОМЕНИ, ЗАЩОТО ЩЕ СЕ ИЗВАЖДА ЕВЕНТУАЛНО ОТ ЖИВОТА НА ИГРАЧА
                    System.out.println("Now your enemy is in \"attack position\" Be careful and choose wisely");
                    System.out.println("Choose defend or attack. 1 is for attack 2 is for defend");
                    yourChose = Byte.parseByte(scanner.nextLine());

                    //ТВОЯ ИЗБОР
                    while (true) {
                        if (yourChose == 1) {
                            yourChoose = "attack";
                            System.out.println("You choose to attack the enemy. You know, the best defense is offense");
                            break;
                        } else if (yourChose == 2) {
                            yourChoose = "defend";
                            System.out.println("You choose to defend yourself. You obviously know that the best offense is defense");
                            break;
                        } else {
                            System.out.println("Invalid input! Try again");
                            yourChose = Byte.parseByte(scanner.nextLine());
                        }
                    }

                    //ИЗБОРА НА БОТА
                    if (botNumChoose <= 5) {
                        System.out.println("Your enemy chose to defend himself");
                        botChoose = "attack";
                    } else {
                        System.out.println("Be careful! The enemy chose to attack you");
                        botChoose = "defend";
                    }

                    //ИЗБОР НА ЛЕВЕЛА ОТ ТЕБЕ
                    System.out.println("Choose your attack level");
                    yourChose = Byte.parseByte(scanner.nextLine());

                    //ПАК СРАВНЯВАНЕ НА РЕЗУЛТАТИТЕ
                    if (!yourChoose.equals(botChoose)) {
                        if (botNumChoose - 1 > yourChose || botNumChoose + 1 < yourChose) {
                            System.out.println("Your enemy choose "+ botNumChoose);
                            yourHealth -= (yourChose + botNumChoose);
                            System.out.printf("Aghhhhhh! You take %d damage. Be careful for the future because you have" +
                                    " %d health left\n", yourChose + botNumChoose, yourHealth);
                        } else {
                            System.out.println("Your enemy choose "+ botNumChoose);
                            System.out.println("What an amazing fight, but neither of you managed to hurt the other enough. " +
                                    "Now rest and think better in the next round");
                        }
                    } else {
                        if (yourChoose.equalsIgnoreCase("attack")) {
                            System.out.println("You both attacked each other at the same time, but neither managed to " +
                                    "take the other down. Good luck in the next round adventurer");
                        } else {
                            System.out.println("You both chose protection! No one attacks the other. Better luck in the next round");
                        }
                    }
                }
            } else {

                //АКО ИЗБЕРЕШ ДА СИ ВТОРИ ЗНАЧИ СИ С ЧЕТНИЯ БРОЙ
                if (countRounds % 2 == 0) {
                    System.out.println("Choose defend or attack. 1 is for attack 2 is for defend");
                    yourChose = Byte.parseByte(scanner.nextLine());

                    while (true) {
                        if (yourChose == 1) {
                            yourChoose = "attack";
                            System.out.println("You choose to attack the enemy. You know, the best defense is offense");
                            break;
                        } else if (yourChose == 2) {
                            yourChoose = "defend";
                            System.out.println("You choose to defend yourself. You obviously know that the best offense is defense");
                            break;
                        } else {
                            System.out.println("Invalid input! Try again");
                            yourChose = Byte.parseByte(scanner.nextLine());
                        }
                    }

                    if (botNumChoose <= 5) {
                        System.out.println("Your enemy chose to defend himself");
                        botChoose = "defend";
                    } else {
                        System.out.println("Be careful! The enemy chose to attack you");
                        botChoose = "attack";
                    }

                    System.out.println("Choose your attack level");
                    yourChose = Byte.parseByte(scanner.nextLine());

                    if (!yourChoose.equals(botChoose)) {
                        if (botNumChoose < yourChose - 1 || botNumChoose > yourChose + 1) {
                            System.out.println("Your enemy choose "+ botNumChoose);
                            healthPC -= (yourChose + botNumChoose);
                            System.out.printf("Well done! Your enemy take %d damage. Now your enemy has %d health\n", yourChose + botNumChoose, healthPC);
                        } else {
                            System.out.println("Your enemy choose "+ botNumChoose);
                            System.out.println("What an amazing fight, but neither of you managed to hurt the other enough. " +
                                    "Now rest and think better in the next round");
                        }
                    } else {
                        if (botChoose.equalsIgnoreCase("attack")) {
                            System.out.println("You both attacked each other at the same time, but neither managed to " +
                                    "take the other down. Good luck in the next round adventurer");
                        } else {
                            System.out.println("You both chose protection! No one attacks the other. Better luck in the next round");
                        }
                    }

                } else {

                    System.out.println("Now your enemy is in \"attack position\" Be careful and choose wisely");
                    System.out.println("Choose defend or attack. 1 is for attack 2 is for defend");
                    yourChose = Byte.parseByte(scanner.nextLine());

                    while (true) {
                        if (yourChose == 1) {
                            yourChoose = "attack";
                            System.out.println("You choose to attack the enemy. You know, the best defense is offense");
                            break;
                        } else if (yourChose == 2) {
                            yourChoose = "defend";
                            System.out.println("You choose to attack the enemy. You know, the best defense is offense");
                            break;
                        } else {
                            System.out.println("Invalid input! Try again");
                            yourChose = Byte.parseByte(scanner.nextLine());
                        }
                    }

                    if (botNumChoose <= 5) {
                        System.out.println("Your enemy chose to defend himself");
                        botChoose = "attack";
                    } else {
                        System.out.println("Be careful! The enemy chose to attack you");
                        botChoose = "defend";
                    }

                    System.out.println("Choose your attack level");
                    yourChose = Byte.parseByte(scanner.nextLine());

                    if (!yourChoose.equals(botChoose)) {
                        if (botNumChoose - 1 > yourChose || botNumChoose + 1 < yourChose) {
                            System.out.println("Your enemy choose "+ botNumChoose);
                            yourHealth -= (yourChose + botNumChoose);
                            System.out.printf("Aghhhhhh! You take %d damage. Be careful for the future because you have" +
                                    " %d health left\n", yourChose + botNumChoose, yourHealth);
                        } else {
                            System.out.println("Your enemy choose "+ botNumChoose);
                            System.out.println("What an amazing fight, but neither of you managed to hurt the other enough. " +
                                    "Now rest and think better in the next round");
                        }
                    } else {
                        if (yourChoose.equalsIgnoreCase("attack")) {
                            System.out.println("You both attacked each other at the same time, but neither managed to " +
                                    "take the other down. Good luck in the next round adventurer");
                        } else {
                            System.out.println("You both chose protection! No one attacks the other. Better luck in the next round");
                        }
                    }
                }
            }

            System.out.printf("Your choose was %s and you have %d hp left!\n", yourChoose, yourHealth);
            System.out.printf("Your enemy was choose %s and now he has %d hp left\n", botChoose, healthPC);
            countRounds++;
        }

        if (healthPC > 0){
            yourHealth = 0;
            System.out.printf("Ahggggg your hp is %d. You died!\n", yourHealth);
        }else {
            healthPC = 0;
            System.out.printf("Well done %s! Your opponent has %d hp. Good job!\n", name, healthPC);
        }
    }
}