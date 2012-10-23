package cz.fjfi.pvs.threads;

import java.util.Deque;
import java.util.LinkedList;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class ProducerConsumerLogging {

	private static Deque<Integer> numbers = new LinkedList<Integer>();

	private static boolean ALL_WAS_PRODUCED = false;
	
	static Logger logger = Logger.getLogger(ProducerConsumerLogging.class);

	public static void main(String[] args) {
		
		BasicConfigurator.configure();
		
		
		String logPattern = "%5p [%t] (%F:%L) - %m%n";		
		PatternLayout pl = new PatternLayout(logPattern);
		ConsoleAppender ca = new ConsoleAppender(pl);
		logger.addAppender(ca);
		logger.info("Starting the application");
		
		
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

		static Logger logger = Logger.getLogger(Producer.class);
		
		public Producer(int order) {
			this.producerId = order;
		}

		public void run() {
			logger.info("Producer " + producerId + " is running");

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
					logger.info("Producer " + producerId + " Working Hard ");
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			logger.info("Producer " + producerId + " is ending");
		}

		private void produce(int i) {
			numbers.add(i);
			logger.info("Producer " + producerId + " Adding " + i);
		}
	}

	public static class Consumer implements Runnable {

		private int consumerId;

		static Logger logger = Logger.getLogger(Consumer.class);
		
		public Consumer(int order) {
			this.consumerId = order;
		}

		public void run() {
			logger.info("is running");

			while (!numbers.isEmpty() || !ALL_WAS_PRODUCED) {
				logger.info("Consumer " + consumerId + " Waiting to consume ");
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
					logger.info("Consumer " + consumerId + " Working Hard");
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			logger.info("Consumer " + consumerId + " ending");
		}

		private void consume() {
			int current = numbers.poll();
			logger.info("Consumer " + consumerId + " Consuming " + current);

		}
	}

}
