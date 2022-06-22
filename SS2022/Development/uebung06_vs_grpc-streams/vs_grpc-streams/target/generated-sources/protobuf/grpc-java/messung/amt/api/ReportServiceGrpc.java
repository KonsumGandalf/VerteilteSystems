package messung.amt.api;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.35.0)",
    comments = "Source: service.proto")
public final class ReportServiceGrpc {

  private ReportServiceGrpc() {}

  public static final String SERVICE_NAME = "messung.amt.api.ReportService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<messung.amt.api.Messwert,
      messung.amt.api.Void> getStoreMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "store",
      requestType = messung.amt.api.Messwert.class,
      responseType = messung.amt.api.Void.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<messung.amt.api.Messwert,
      messung.amt.api.Void> getStoreMethod() {
    io.grpc.MethodDescriptor<messung.amt.api.Messwert, messung.amt.api.Void> getStoreMethod;
    if ((getStoreMethod = ReportServiceGrpc.getStoreMethod) == null) {
      synchronized (ReportServiceGrpc.class) {
        if ((getStoreMethod = ReportServiceGrpc.getStoreMethod) == null) {
          ReportServiceGrpc.getStoreMethod = getStoreMethod =
              io.grpc.MethodDescriptor.<messung.amt.api.Messwert, messung.amt.api.Void>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "store"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  messung.amt.api.Messwert.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  messung.amt.api.Void.getDefaultInstance()))
              .setSchemaDescriptor(new ReportServiceMethodDescriptorSupplier("store"))
              .build();
        }
      }
    }
    return getStoreMethod;
  }

  private static volatile io.grpc.MethodDescriptor<messung.amt.api.Messwert,
      messung.amt.api.Bericht> getAnalyseDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "analyseData",
      requestType = messung.amt.api.Messwert.class,
      responseType = messung.amt.api.Bericht.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<messung.amt.api.Messwert,
      messung.amt.api.Bericht> getAnalyseDataMethod() {
    io.grpc.MethodDescriptor<messung.amt.api.Messwert, messung.amt.api.Bericht> getAnalyseDataMethod;
    if ((getAnalyseDataMethod = ReportServiceGrpc.getAnalyseDataMethod) == null) {
      synchronized (ReportServiceGrpc.class) {
        if ((getAnalyseDataMethod = ReportServiceGrpc.getAnalyseDataMethod) == null) {
          ReportServiceGrpc.getAnalyseDataMethod = getAnalyseDataMethod =
              io.grpc.MethodDescriptor.<messung.amt.api.Messwert, messung.amt.api.Bericht>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "analyseData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  messung.amt.api.Messwert.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  messung.amt.api.Bericht.getDefaultInstance()))
              .setSchemaDescriptor(new ReportServiceMethodDescriptorSupplier("analyseData"))
              .build();
        }
      }
    }
    return getAnalyseDataMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ReportServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ReportServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ReportServiceStub>() {
        @java.lang.Override
        public ReportServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ReportServiceStub(channel, callOptions);
        }
      };
    return ReportServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ReportServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ReportServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ReportServiceBlockingStub>() {
        @java.lang.Override
        public ReportServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ReportServiceBlockingStub(channel, callOptions);
        }
      };
    return ReportServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ReportServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ReportServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ReportServiceFutureStub>() {
        @java.lang.Override
        public ReportServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ReportServiceFutureStub(channel, callOptions);
        }
      };
    return ReportServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ReportServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void store(messung.amt.api.Messwert request,
        io.grpc.stub.StreamObserver<messung.amt.api.Void> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStoreMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<messung.amt.api.Messwert> analyseData(
        io.grpc.stub.StreamObserver<messung.amt.api.Bericht> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getAnalyseDataMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getStoreMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                messung.amt.api.Messwert,
                messung.amt.api.Void>(
                  this, METHODID_STORE)))
          .addMethod(
            getAnalyseDataMethod(),
            io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
              new MethodHandlers<
                messung.amt.api.Messwert,
                messung.amt.api.Bericht>(
                  this, METHODID_ANALYSE_DATA)))
          .build();
    }
  }

  /**
   */
  public static final class ReportServiceStub extends io.grpc.stub.AbstractAsyncStub<ReportServiceStub> {
    private ReportServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReportServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ReportServiceStub(channel, callOptions);
    }

    /**
     */
    public void store(messung.amt.api.Messwert request,
        io.grpc.stub.StreamObserver<messung.amt.api.Void> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getStoreMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<messung.amt.api.Messwert> analyseData(
        io.grpc.stub.StreamObserver<messung.amt.api.Bericht> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getAnalyseDataMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class ReportServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<ReportServiceBlockingStub> {
    private ReportServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReportServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ReportServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public messung.amt.api.Void store(messung.amt.api.Messwert request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getStoreMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ReportServiceFutureStub extends io.grpc.stub.AbstractFutureStub<ReportServiceFutureStub> {
    private ReportServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReportServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ReportServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<messung.amt.api.Void> store(
        messung.amt.api.Messwert request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getStoreMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_STORE = 0;
  private static final int METHODID_ANALYSE_DATA = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ReportServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ReportServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_STORE:
          serviceImpl.store((messung.amt.api.Messwert) request,
              (io.grpc.stub.StreamObserver<messung.amt.api.Void>) responseObserver);
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
        case METHODID_ANALYSE_DATA:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.analyseData(
              (io.grpc.stub.StreamObserver<messung.amt.api.Bericht>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ReportServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ReportServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return messung.amt.api.Service.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ReportService");
    }
  }

  private static final class ReportServiceFileDescriptorSupplier
      extends ReportServiceBaseDescriptorSupplier {
    ReportServiceFileDescriptorSupplier() {}
  }

  private static final class ReportServiceMethodDescriptorSupplier
      extends ReportServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ReportServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ReportServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ReportServiceFileDescriptorSupplier())
              .addMethod(getStoreMethod())
              .addMethod(getAnalyseDataMethod())
              .build();
        }
      }
    }
    return result;
  }
}
