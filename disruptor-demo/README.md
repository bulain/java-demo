## LMAX Disruptor - A High Performance Inter-Thread Messaging Library
> LMAX Disruptor - 一种高性能的线程间消息库

## Key Features:
* Multicast events to consumers, with consumer dependency graph.
> 同一个"事件"可以有多个消费者，消费者之间既可以并行处理，也可以相互依赖形成处理的先后次序。
* Pre-allocate memory for events.
>  预分配用于存储事件内容的内存空间。
* Optionally lock-free.
> 针对极高的性能目标而实现的极度优化和无锁的设计。

## Core Concepts
* Ring Buffer: The Ring Buffer is often considered the main aspect of the Disruptor, however from 3.0 onwards the Ring Buffer is only responsible for the storing and updating of the data (Events) that move through the Disruptor. And for some advanced use cases can be completely replaced by the user.
> RingBuffer: Disruptor底层数据结构实现，核心类，是线程间交换数据的中转地。
* Sequence: The Disruptor uses Sequences as a means to identify where a particular component is up to. Each consumer (EventProcessor) maintains a Sequence as does the Disruptor itself. The majority of the concurrent code relies on the the movement of these Sequence values, hence the Sequence supports many of the current features of an AtomicLong. In fact the only real difference between the 2 is that the Sequence contains additional functionality to prevent false sharing between Sequences and other values.
> Sequence: 序号，声明一个序号，用于跟踪ringbuffer中任务的变化和消费者的消费情况。
* Sequencer: The Sequencer is the real core of the Disruptor. The 2 implementations (single producer, multi producer) of this interface implement all of the concurrent algorithms use for fast, correct passing of data between producers and consumers.
> Sequencer: 序号管理器，负责消费者/生产者各自序号、序号栅栏的管理和协调。
* Sequence Barrier: The Sequence Barrier is produced by the Sequencer and contains references to the main published Sequence from the Sequencer and the Sequences of any dependent consumer. It contains the logic to determine if there are any events available for the consumer to process.
> SequenceBarrier: 序号栅栏，管理和协调生产者的游标序号和各个消费者的序号，确保生产者不会覆盖消费者未来得及处理的消息，确保存在依赖的消费者之间能够按照正确的顺序处理。
* Wait Strategy: The Wait Strategy determines how a consumer will wait for events to be placed into the Disruptor by a producer. More details are available in the section about being optionally lock-free.
> Wait Strategy: WaitStrategy有多种实现，用以表示当无可消费事件时，消费者的等待策略。
* Event: The unit of data passed from producer to consumer. There is no specific code representation of the Event as it defined entirely by the user.
> Event: 消费事件，Event的具体实现由用户定义。
* EventProcessor: The main event loop for handling events from the Disruptor and has ownership of consumer's Sequence. There is a single representation called BatchEventProcessor that contains an efficient implementation of the event loop and will call back onto a used supplied implementation of the EventHandler interface.
> EventProcessor: 事件处理器，监听RingBuffer的事件，并消费可用事件，从RingBuffer读取的事件会交由实际的生产者实现类来消费；它会一直侦听下一个可用的序号，直到该序号对应的事件已经准备好。
* EventHandler: An interface that is implemented by the user and represents a consumer for the Disruptor.
> EventHandler: 业务处理器，是实际消费者的接口，完成具体的业务逻辑实现，第三方实现该接口；代表着消费者。
* Producer: This is the user code that calls the Disruptor to enqueue Events. This concept also has no representation in the code.
> Producer: 生产者接口，第三方线程充当该角色，producer向RingBuffer写入事件。
* Disruptor: 
> Disruptor: Disruptor的使用入口。持有RingBuffer、消费者线程池Executor、消费者集合ConsumerRepository等引用。
