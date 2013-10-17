package org.jevo.annotation.sample;

import org.jevo.enusample.Enums;

import java.util.EnumMap;
import java.util.Random;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-7
 * Time: 10:22:12
 * To change this template use File | Settings | File Templates.
 */


enum Input { //售货机可以接收的钞票金额和所有商品的价格
    NICKEL(5), //五分
    DIME(10), //一角
    QUARTER(25), //两角五分
    DOLLAR(100),//一块

    TOOTHPASTE(200), //药膏2元
    CHIPS(75),   //炸薯条75美分
    SODA(100),// 苏打水1元
    SOAP(50), //肥皂5毛

    ABORT_TRANSACTION {
        public int amount() {
            throw new RuntimeException("退出时不能获取余额！");
        }
    },
    STOP {
        public int amount() {
            throw new RuntimeException("关机时不能获取余额！");
        }
    };

    //金额
    int value;

    Input(int value) {
        this.value = value;
    }

    Input() {
    }

    //返回该操作项的金额
    int amount() {
        return value;
    }

    /**
     * @return 随机获取的操作
     */
    public static Input randomSelection() {
        return values()[new Random(System.nanoTime()).nextInt(values().length)];
    }
}

enum Category { //自动售货机的状态
    MONEY(Input.NICKEL, Input.DIME, Input.QUARTER, Input.DOLLAR), //放入钞票*

    ITEM_SELECTION(Input.TOOTHPASTE, Input.CHIPS, Input.SODA, Input.SOAP), //选择商品

    QUIT_TRANSACTION(Input.ABORT_TRANSACTION), //退出

    SHUT_DOWN(Input.STOP); //关机

    private Input[] values;


    Category(Input... types) {
        values = types;
    }

    public static EnumMap<Input, Category> categories = new EnumMap<Input, Category>(Input.class);

    public Input[] getValues() {
        return values;
    }

    //初始化自动售货机状态集合
    static {
        for (Category c : Category.class.getEnumConstants()) {
            for (Input input : c.values) {
                categories.put(input, c);
            }
        }
    }

    /**
     * 返回该操作项所属状态*
     */
    public static Category categorize(Input input) {
        return categories.get(input);
    }
}

//自动售货机:VendingMachine用来对输入进行相应，首先通过Category枚举归类输入，然后使用switch语句
public class VendingMachine {

    //当前运行状态
    private static State state = State.RESTING;
    //当前余额
    private static int amount = 0;
    //当前选择商品
    private static Input selection = null;

    enum StateDuration { //持续状态，不能做其他操作
        TRANSIENT
    }

    enum State { //运行状态
        RESTING {
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        System.out.println("放入金额：" + input.amount() + "美分");
                        state = ADDING_MONEY;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                        break;
                    default:
                        state = RESTING;
                        break;
                }
            }
        },

        ADDING_MONEY {  //选择商品
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        System.out.println("再次放入金额：" + input.amount() + "美分，您的余额是：" + amount + "美分");
                        break;
                    case ITEM_SELECTION:
                        selection = input;
                        System.out.println("选择商品：" + input);
                        if (amount < input.amount()) {
                            System.out.println("你的余额不够购买商品：" + input);
                            state = ADDING_MONEY;
                        } else state = DISPENSING;
                        break;
                    case QUIT_TRANSACTION:
                        state = GIVING_CHANGE;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                        break;
                    default:
                        state = ADDING_MONEY;
                        break;
                }
            }
        },

        DISPENSING(StateDuration.TRANSIENT) { //发出商品，交易成功
            void next() {
                System.out.println("交易成功！请拿好您的商品：" + selection);
                //扣除购买商品的金额
                amount -= selection.amount();
                state = GIVING_CHANGE;
            }
        },

        GIVING_CHANGE(StateDuration.TRANSIENT) {  //找零
            void next() {
                if (amount > 0) {
                    System.out.println("请拿好您的找零：" + amount + "美分");
                    amount = 0;
                }
                state = TERMINAL;
            }
        },

        TERMINAL { //交易终止
            void output() {
                System.out.println("交易结束");
            }
        };

        private boolean isTransient = false;

        public boolean isTransient() { //当前是否是瞬时状态（即不可以做其他操作）
            return this.isTransient;
        }

        State() {
        }

        State(StateDuration stateDuration) {
            this.isTransient = true;
        }

        //默认方法（在瞬时状态时做其他操作时被调用）
        void next(Input input) {
            System.out.println("该状态不能做其他操作！");
        }

        //默认方法（在非瞬时状态时不做操作时被调用）
        void next() {
            System.out.println("请选择一个操作！");
        }

        //默认方法（查看余额）
        void output() {
            System.out.println("您的余额还剩：" + amount + "美分");
        }
    }

    //执行一个操作
    public static void run(Input gen) {
        if (state != State.TERMINAL) {
            if (state.isTransient()) {
                state.next();
            } else {
                state.next(gen);
            }

        } else {
            state.output();
        }

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int i = 0;
        while (true) {
            switch (state) {
                case RESTING:
                    run(Enums.random(Category.MONEY.getValues()));
                    break;
                case ADDING_MONEY:
                    //如果金额不足
                    if (i > 0) {
                        run(Enums.random(Category.MONEY.getValues()));
                        i = 0;
                    } else {
                        run(Enums.random(Category.ITEM_SELECTION.getValues()));
                        i++;
                    }
                    break;
                case TERMINAL:
                    run(Input.STOP);
                    return;
                default:
                    run(null);
                    break;
            }
        }
    }
}




