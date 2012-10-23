package cz.fjfi.pvs.threads;

import java.util.Deque;
import java.util.LinkedList;

public class ProducerConsumer {

	private static Deque<Integer> numbers = new LinkedList<Integer>();

	private static boolean ALL_WAS_PRODUCED = false;

	public static void main(String[] args) {
		Producer p1 = new Producer(1);
		Consumer c1 = new Consumer(1);
		Consumer c2 = new Consumer(2);

		new Thread(p1).start();
		new Thread(c1).start();
		new Thread(c2).start();

	}

	public static class Producer implements Runnable {

		private int producerId;

		private static int THRESHOLD = 10;

		public Producer(int order) {
			this.producerId = order;
		}

		public void run() {
			System.out.println("Producer " + producerId + " is running");

			for (int i = 0; i < THRESHOLD; i++) {
				synchronized (numbers) {
					produce(i);

					if (THRESHOLD == (i + 1)) {
						ALL_WAS_PRODUCED = true;
						// numbers.notify();
						break;
					}
				}
				// numbers.notify();
				// try {
				// numbers.wait();
				// } catch (InterruptedException e) {
				// System.out.println("Producer Interrupted");
				// }
				try {
					System.out.println("Producer " + producerId + " Working Hard ");
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			System.out.println("Producer " + producerId + " is ending");
		}

		private void produce(int i) {
			numbers.add(i);
			System.out.println("Producer " + producerId + " Adding " + i);
		}
	}

	public static class Consumer implements Runnable {

		private int consumerId;

		public Consumer(int order) {
			this.consumerId = order;
		}

		public void run() {
			System.out.println("Consumer " + consumerId + " is running");

			while (!numbers.isEmpty() || !ALL_WAS_PRODUCED) {
				System.out.println("Consumer " + consumerId + " Waiting to consume ");
				synchronized (numbers) {
					if (!numbers.isEmpty()) {
						consume();
					}
					// numbers.notify();
					// try {
					// System.out.println("Consumer " + consumerId +
					// " Sleeping ");
					// numbers.wait();
					// System.out.println("Consumer " + consumerId +
					// " Waking ");
					// } catch (InterruptedException e) {
					// System.out.println("Interrupt");
					// }
				}
				try {
					System.out.println("Consumer " + consumerId + " Working Hard ");
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

			synchronized (numbers) {
				numbers.notifyAll();
			}

			System.out.println("Consumer " + consumerId + " ending");
		}

		private void consume() {
			int current = numbers.poll();
			System.out.println("Consumer " + consumerId + " Consuming " + current);

		}
	}

}
