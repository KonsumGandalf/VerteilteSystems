package bring.me.api.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * für Streaming is Blocking nicht möglich! =&gt; onNext in OrderServiceImpl mehrmals aufrufbar
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.35.0)",
    comments = "Source: sample.proto")
public final class OrderServiceGrpc {

  private OrderServiceGrpc() {}

  public static final String SERVICE_NAME = "bring.me.api.grpc.OrderService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<bring.me.api.grpc.Order,
      bring.me.api.grpc.Invoice> getOrderDishMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "orderDish",
      requestType = bring.me.api.grpc.Order.class,
      responseType = bring.me.api.grpc.Invoice.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<bring.me.api.grpc.Order,
      bring.me.api.grpc.Invoice> getOrderDishMethod() {
    io.grpc.MethodDescriptor<bring.me.api.grpc.Order, bring.me.api.grpc.Invoice> getOrderDishMethod;
    if ((getOrderDishMethod = OrderServiceGrpc.getOrderDishMethod) == null) {
      synchronized (OrderServiceGrpc.class) {
        if ((getOrderDishMethod = OrderServiceGrpc.getOrderDishMethod) == null) {
          OrderServiceGrpc.getOrderDishMethod = getOrderDishMethod =
              io.grpc.MethodDescriptor.<bring.me.api.grpc.Order, bring.me.api.grpc.Invoice>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "orderDish"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bring.me.api.grpc.Order.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bring.me.api.grpc.Invoice.getDefaultInstance()))
              .setSchemaDescriptor(new OrderServiceMethodDescriptorSupplier("orderDish"))
              .build();
        }
      }
    }
    return getOrderDishMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bring.me.api.grpc.Order,
      bring.me.api.grpc.Invoice> getBusinessOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "businessOrder",
      requestType = bring.me.api.grpc.Order.class,
      responseType = bring.me.api.grpc.Invoice.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<bring.me.api.grpc.Order,
      bring.me.api.grpc.Invoice> getBusinessOrderMethod() {
    io.grpc.MethodDescriptor<bring.me.api.grpc.Order, bring.me.api.grpc.Invoice> getBusinessOrderMethod;
    if ((getBusinessOrderMethod = OrderServiceGrpc.getBusinessOrderMethod) == null) {
      synchronized (OrderServiceGrpc.class) {
        if ((getBusinessOrderMethod = OrderServiceGrpc.getBusinessOrderMethod) == null) {
          OrderServiceGrpc.getBusinessOrderMethod = getBusinessOrderMethod =
              io.grpc.MethodDescriptor.<bring.me.api.grpc.Order, bring.me.api.grpc.Invoice>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "businessOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bring.me.api.grpc.Order.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bring.me.api.grpc.Invoice.getDefaultInstance()))
              .setSchemaDescriptor(new OrderServiceMethodDescriptorSupplier("businessOrder"))
              .build();
        }
      }
    }
    return getBusinessOrderMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static OrderServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OrderServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OrderServiceStub>() {
        @java.lang.Override
        public OrderServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OrderServiceStub(channel, callOptions);
        }
      };
    return OrderServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static OrderServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OrderServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OrderServiceBlockingStub>() {
        @java.lang.Override
        public OrderServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OrderServiceBlockingStub(channel, callOptions);
        }
      };
    return OrderServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static OrderServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OrderServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OrderServiceFutureStub>() {
        @java.lang.Override
        public OrderServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OrderServiceFutureStub(channel, callOptions);
        }
      };
    return OrderServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * für Streaming is Blocking nicht möglich! =&gt; onNext in OrderServiceImpl mehrmals aufrufbar
   * </pre>
   */
  public static abstract class OrderServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void orderDish(bring.me.api.grpc.Order request,
        io.grpc.stub.StreamObserver<bring.me.api.grpc.Invoice> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOrderDishMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<bring.me.api.grpc.Order> businessOrder(
        io.grpc.stub.StreamObserver<bring.me.api.grpc.Invoice> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getBusinessOrderMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getOrderDishMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                bring.me.api.grpc.Order,
                bring.me.api.grpc.Invoice>(
                  this, METHODID_ORDER_DISH)))
          .addMethod(
            getBusinessOrderMethod(),
            io.grpc.stub.ServerCalls.asyncClientStreamingCall(
              new MethodHandlers<
                bring.me.api.grpc.Order,
                bring.me.api.grpc.Invoice>(
                  this, METHODID_BUSINESS_ORDER)))
          .build();
    }
  }

  /**
   * <pre>
   * für Streaming is Blocking nicht möglich! =&gt; onNext in OrderServiceImpl mehrmals aufrufbar
   * </pre>
   */
  public static final class OrderServiceStub extends io.grpc.stub.AbstractAsyncStub<OrderServiceStub> {
    private OrderServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OrderServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OrderServiceStub(channel, callOptions);
    }

    /**
     */
    public void orderDish(bring.me.api.grpc.Order request,
        io.grpc.stub.StreamObserver<bring.me.api.grpc.Invoice> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getOrderDishMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<bring.me.api.grpc.Order> businessOrder(
        io.grpc.stub.StreamObserver<bring.me.api.grpc.Invoice> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getBusinessOrderMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   * für Streaming is Blocking nicht möglich! =&gt; onNext in OrderServiceImpl mehrmals aufrufbar
   * </pre>
   */
  public static final class OrderServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<OrderServiceBlockingStub> {
    private OrderServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OrderServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OrderServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<bring.me.api.grpc.Invoice> orderDish(
        bring.me.api.grpc.Order request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getOrderDishMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * für Streaming is Blocking nicht möglich! =&gt; onNext in OrderServiceImpl mehrmals aufrufbar
   * </pre>
   */
  public static final class OrderServiceFutureStub extends io.grpc.stub.AbstractFutureStub<OrderServiceFutureStub> {
    private OrderServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OrderServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OrderServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_ORDER_DISH = 0;
  private static final int METHODID_BUSINESS_ORDER = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final OrderServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(OrderServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ORDER_DISH:
          serviceImpl.orderDish((bring.me.api.grpc.Order) request,
              (io.grpc.stub.StreamObserver<bring.me.api.grpc.Invoice>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_BUSINESS_ORDER:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.businessOrder(
              (io.grpc.stub.StreamObserver<bring.me.api.grpc.Invoice>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class OrderServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    OrderServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return bring.me.api.grpc.Sample.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("OrderService");
    }
  }

  private static final class OrderServiceFileDescriptorSupplier
      extends OrderServiceBaseDescriptorSupplier {
    OrderServiceFileDescriptorSupplier() {}
  }

  private static final class OrderServiceMethodDescriptorSupplier
      extends OrderServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    OrderServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (OrderServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new OrderServiceFileDescriptorSupplier())
              .addMethod(getOrderDishMethod())
              .addMethod(getBusinessOrderMethod())
              .build();
        }
      }
    }
    return result;
  }
}
