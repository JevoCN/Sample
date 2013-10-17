package org.jevo.annotation.sample;

import org.jevo.enusample.Enums;

import java.util.EnumMap;
import java.util.Random;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-7
 * Time: 10:22:12
 * To change this template use File | Settings | File Templates.
 */


enum Input { //�ۻ������Խ��յĳ�Ʊ����������Ʒ�ļ۸�
    NICKEL(5), //���
    DIME(10), //һ��
    QUARTER(25), //�������
    DOLLAR(100),//һ��

    TOOTHPASTE(200), //ҩ��2Ԫ
    CHIPS(75),   //ը����75����
    SODA(100),// �մ�ˮ1Ԫ
    SOAP(50), //����5ë

    ABORT_TRANSACTION {
        public int amount() {
            throw new RuntimeException("�˳�ʱ���ܻ�ȡ��");
        }
    },
    STOP {
        public int amount() {
            throw new RuntimeException("�ػ�ʱ���ܻ�ȡ��");
        }
    };

    //���
    int value;

    Input(int value) {
        this.value = value;
    }

    Input() {
    }

    //���ظò�����Ľ��
    int amount() {
        return value;
    }

    /**
     * @return �����ȡ�Ĳ���
     */
    public static Input randomSelection() {
        return values()[new Random(System.nanoTime()).nextInt(values().length)];
    }
}

enum Category { //�Զ��ۻ�����״̬
    MONEY(Input.NICKEL, Input.DIME, Input.QUARTER, Input.DOLLAR), //���볮Ʊ*

    ITEM_SELECTION(Input.TOOTHPASTE, Input.CHIPS, Input.SODA, Input.SOAP), //ѡ����Ʒ

    QUIT_TRANSACTION(Input.ABORT_TRANSACTION), //�˳�

    SHUT_DOWN(Input.STOP); //�ػ�

    private Input[] values;


    Category(Input... types) {
        values = types;
    }

    public static EnumMap<Input, Category> categories = new EnumMap<Input, Category>(Input.class);

    public Input[] getValues() {
        return values;
    }

    //��ʼ���Զ��ۻ���״̬����
    static {
        for (Category c : Category.class.getEnumConstants()) {
            for (Input input : c.values) {
                categories.put(input, c);
            }
        }
    }

    /**
     * ���ظò���������״̬*
     */
    public static Category categorize(Input input) {
        return categories.get(input);
    }
}

//�Զ��ۻ���:VendingMachine���������������Ӧ������ͨ��Categoryö�ٹ������룬Ȼ��ʹ��switch���
public class VendingMachine {

    //��ǰ����״̬
    private static State state = State.RESTING;
    //��ǰ���
    private static int amount = 0;
    //��ǰѡ����Ʒ
    private static Input selection = null;

    enum StateDuration { //����״̬����������������
        TRANSIENT
    }

    enum State { //����״̬
        RESTING {
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        System.out.println("�����" + input.amount() + "����");
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

        ADDING_MONEY {  //ѡ����Ʒ
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        System.out.println("�ٴη����" + input.amount() + "���֣���������ǣ�" + amount + "����");
                        break;
                    case ITEM_SELECTION:
                        selection = input;
                        System.out.println("ѡ����Ʒ��" + input);
                        if (amount < input.amount()) {
                            System.out.println("�������������Ʒ��" + input);
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

        DISPENSING(StateDuration.TRANSIENT) { //������Ʒ�����׳ɹ�
            void next() {
                System.out.println("���׳ɹ������ú�������Ʒ��" + selection);
                //�۳�������Ʒ�Ľ��
                amount -= selection.amount();
                state = GIVING_CHANGE;
            }
        },

        GIVING_CHANGE(StateDuration.TRANSIENT) {  //����
            void next() {
                if (amount > 0) {
                    System.out.println("���ú��������㣺" + amount + "����");
                    amount = 0;
                }
                state = TERMINAL;
            }
        },

        TERMINAL { //������ֹ
            void output() {
                System.out.println("���׽���");
            }
        };

        private boolean isTransient = false;

        public boolean isTransient() { //��ǰ�Ƿ���˲ʱ״̬����������������������
            return this.isTransient;
        }

        State() {
        }

        State(StateDuration stateDuration) {
            this.isTransient = true;
        }

        //Ĭ�Ϸ�������˲ʱ״̬ʱ����������ʱ�����ã�
        void next(Input input) {
            System.out.println("��״̬����������������");
        }

        //Ĭ�Ϸ������ڷ�˲ʱ״̬ʱ��������ʱ�����ã�
        void next() {
            System.out.println("��ѡ��һ��������");
        }

        //Ĭ�Ϸ������鿴��
        void output() {
            System.out.println("������ʣ��" + amount + "����");
        }
    }

    //ִ��һ������
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
                    //�������
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




