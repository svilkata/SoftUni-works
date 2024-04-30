import java.util.concurrent.CompletableFuture;

public class Main {
    public static CompletableFuture<Integer> create() {
        return CompletableFuture.supplyAsync(() -> 2);
    }

    public static CompletableFuture<Integer> create(int number) {
        return CompletableFuture.supplyAsync(() -> number);
    }

    public static CompletableFuture<Integer> inc(int number){
        return CompletableFuture.supplyAsync(() -> number + 1);
    }

    public static void main(String[] args) throws InterruptedException {
        create(2)
//                .thenApply(data -> inc(data))
                .thenCompose(data -> inc(data))
                .thenAccept(result -> System.out.println(result));


//        CompletableFuture<Integer> future = new CompletableFuture<Integer>();
//        future
//                .thenApply(d -> d * 2)
//                .thenApply(d -> d + 1)
//                .thenAccept(data -> System.out.println(data));
//        System.out.println("We built the pipeline");
//        System.out.println("Prepared to print");
//
//        sleep(1000);
//
//        future.complete(2);
//        sleep(1000);


//        CompletableFuture<Integer> future = create();
//        CompletableFuture<Void> future2 = future.thenAccept(data -> System.out.println(data));
//
//        create()
//                .thenApply(d -> d * 10)
//                .thenAccept(data -> System.out.println(data))
//                .thenRun(() -> System.out.println("This never dies"));


    }

    private static Integer handleException(Throwable thr) {
        System.out.println("ERROR: " + thr);
        throw new RuntimeException("It is beoynd all hope");
    }

    private static void sleep(int i) throws InterruptedException {
        Thread.sleep(i);
    }
}
