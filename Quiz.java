import java.util.*;
class Quiz {
    static class Question {
        String question;
        String[] options;
        int correctAnswer;

        public Question(String question, String[] options, int correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Question> questions = new ArrayList<>();
    private static int score = 0;

    public static void main(String[] args) {
        loadQuestions();
        startQuiz();
        showResult();
    }

    private static void loadQuestions() {
        questions.add(new Question("What is the capital of France?", new String[]{"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"}, 3));
        questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"1. Earth", "2. Mars", "3. Jupiter", "4. Venus"}, 2));
        questions.add(new Question("What is the largest mammal?", new String[]{"1. Elephant", "2. Blue Whale", "3. Giraffe", "4. Shark"}, 2));
    }

    private static void startQuiz() {
        for (Question q : questions) {
            askQuestion(q);
        }
    }

    private static void askQuestion(Question q) {
        System.out.println(q.question);
        for (String option : q.options) {
            System.out.println(option);
        }
        System.out.print("Your answer (enter option number): ");

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime is up!");
                synchronized (scanner) {
                    scanner.notify();
                }
            }
        };

        timer.schedule(task, 10000); // 10 seconds timer
        int answer = -1;

        synchronized (scanner) {
            if (scanner.hasNextInt()) {
                answer = scanner.nextInt();
                scanner.notify();
            }
        }
        timer.cancel();

        if (answer == q.correctAnswer) {
            System.out.println("Correct!\n");
            score++;
        } else {
            System.out.println("Wrong! The correct answer was: " + q.correctAnswer + "\n");
        }
    }

    private static void showResult() {
        System.out.println("Quiz Over!");
        System.out.println("Your final score: " + score + "/" + questions.size());
    }
}
