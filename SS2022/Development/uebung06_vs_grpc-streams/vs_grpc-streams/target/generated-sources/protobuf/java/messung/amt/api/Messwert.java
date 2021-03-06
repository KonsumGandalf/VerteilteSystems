// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service.proto

package messung.amt.api;

/**
 * Protobuf type {@code messung.amt.api.Messwert}
 */
public final class Messwert extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:messung.amt.api.Messwert)
    MesswertOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Messwert.newBuilder() to construct.
  private Messwert(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Messwert() {
    uhrzeit_ = "";
    author_ = "";
    ort_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Messwert();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Messwert(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            id_ = input.readInt32();
            break;
          }
          case 16: {

            temperature_ = input.readInt32();
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            uhrzeit_ = s;
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            author_ = s;
            break;
          }
          case 42: {
            java.lang.String s = input.readStringRequireUtf8();

            ort_ = s;
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return messung.amt.api.Service.internal_static_messung_amt_api_Messwert_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return messung.amt.api.Service.internal_static_messung_amt_api_Messwert_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            messung.amt.api.Messwert.class, messung.amt.api.Messwert.Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private int id_;
  /**
   * <code>int32 id = 1;</code>
   * @return The id.
   */
  @java.lang.Override
  public int getId() {
    return id_;
  }

  public static final int TEMPERATURE_FIELD_NUMBER = 2;
  private int temperature_;
  /**
   * <code>int32 temperature = 2;</code>
   * @return The temperature.
   */
  @java.lang.Override
  public int getTemperature() {
    return temperature_;
  }

  public static final int UHRZEIT_FIELD_NUMBER = 3;
  private volatile java.lang.Object uhrzeit_;
  /**
   * <code>string uhrzeit = 3;</code>
   * @return The uhrzeit.
   */
  @java.lang.Override
  public java.lang.String getUhrzeit() {
    java.lang.Object ref = uhrzeit_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      uhrzeit_ = s;
      return s;
    }
  }
  /**
   * <code>string uhrzeit = 3;</code>
   * @return The bytes for uhrzeit.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getUhrzeitBytes() {
    java.lang.Object ref = uhrzeit_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      uhrzeit_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int AUTHOR_FIELD_NUMBER = 4;
  private volatile java.lang.Object author_;
  /**
   * <code>string author = 4;</code>
   * @return The author.
   */
  @java.lang.Override
  public java.lang.String getAuthor() {
    java.lang.Object ref = author_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      author_ = s;
      return s;
    }
  }
  /**
   * <code>string author = 4;</code>
   * @return The bytes for author.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getAuthorBytes() {
    java.lang.Object ref = author_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      author_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int ORT_FIELD_NUMBER = 5;
  private volatile java.lang.Object ort_;
  /**
   * <code>string ort = 5;</code>
   * @return The ort.
   */
  @java.lang.Override
  public java.lang.String getOrt() {
    java.lang.Object ref = ort_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      ort_ = s;
      return s;
    }
  }
  /**
   * <code>string ort = 5;</code>
   * @return The bytes for ort.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getOrtBytes() {
    java.lang.Object ref = ort_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      ort_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (id_ != 0) {
      output.writeInt32(1, id_);
    }
    if (temperature_ != 0) {
      output.writeInt32(2, temperature_);
    }
    if (!getUhrzeitBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, uhrzeit_);
    }
    if (!getAuthorBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, author_);
    }
    if (!getOrtBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, ort_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (id_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, id_);
    }
    if (temperature_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, temperature_);
    }
    if (!getUhrzeitBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, uhrzeit_);
    }
    if (!getAuthorBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, author_);
    }
    if (!getOrtBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, ort_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof messung.amt.api.Messwert)) {
      return super.equals(obj);
    }
    messung.amt.api.Messwert other = (messung.amt.api.Messwert) obj;

    if (getId()
        != other.getId()) return false;
    if (getTemperature()
        != other.getTemperature()) return false;
    if (!getUhrzeit()
        .equals(other.getUhrzeit())) return false;
    if (!getAuthor()
        .equals(other.getAuthor())) return false;
    if (!getOrt()
        .equals(other.getOrt())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ID_FIELD_NUMBER;
    hash = (53 * hash) + getId();
    hash = (37 * hash) + TEMPERATURE_FIELD_NUMBER;
    hash = (53 * hash) + getTemperature();
    hash = (37 * hash) + UHRZEIT_FIELD_NUMBER;
    hash = (53 * hash) + getUhrzeit().hashCode();
    hash = (37 * hash) + AUTHOR_FIELD_NUMBER;
    hash = (53 * hash) + getAuthor().hashCode();
    hash = (37 * hash) + ORT_FIELD_NUMBER;
    hash = (53 * hash) + getOrt().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static messung.amt.api.Messwert parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static messung.amt.api.Messwert parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static messung.amt.api.Messwert parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static messung.amt.api.Messwert parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static messung.amt.api.Messwert parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static messung.amt.api.Messwert parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static messung.amt.api.Messwert parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static messung.amt.api.Messwert parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static messung.amt.api.Messwert parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static messung.amt.api.Messwert parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static messung.amt.api.Messwert parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static messung.amt.api.Messwert parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(messung.amt.api.Messwert prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code messung.amt.api.Messwert}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:messung.amt.api.Messwert)
      messung.amt.api.MesswertOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return messung.amt.api.Service.internal_static_messung_amt_api_Messwert_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return messung.amt.api.Service.internal_static_messung_amt_api_Messwert_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              messung.amt.api.Messwert.class, messung.amt.api.Messwert.Builder.class);
    }

    // Construct using messung.amt.api.Messwert.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      id_ = 0;

      temperature_ = 0;

      uhrzeit_ = "";

      author_ = "";

      ort_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return messung.amt.api.Service.internal_static_messung_amt_api_Messwert_descriptor;
    }

    @java.lang.Override
    public messung.amt.api.Messwert getDefaultInstanceForType() {
      return messung.amt.api.Messwert.getDefaultInstance();
    }

    @java.lang.Override
    public messung.amt.api.Messwert build() {
      messung.amt.api.Messwert result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public messung.amt.api.Messwert buildPartial() {
      messung.amt.api.Messwert result = new messung.amt.api.Messwert(this);
      result.id_ = id_;
      result.temperature_ = temperature_;
      result.uhrzeit_ = uhrzeit_;
      result.author_ = author_;
      result.ort_ = ort_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof messung.amt.api.Messwert) {
        return mergeFrom((messung.amt.api.Messwert)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(messung.amt.api.Messwert other) {
      if (other == messung.amt.api.Messwert.getDefaultInstance()) return this;
      if (other.getId() != 0) {
        setId(other.getId());
      }
      if (other.getTemperature() != 0) {
        setTemperature(other.getTemperature());
      }
      if (!other.getUhrzeit().isEmpty()) {
        uhrzeit_ = other.uhrzeit_;
        onChanged();
      }
      if (!other.getAuthor().isEmpty()) {
        author_ = other.author_;
        onChanged();
      }
      if (!other.getOrt().isEmpty()) {
        ort_ = other.ort_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      messung.amt.api.Messwert parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (messung.amt.api.Messwert) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int id_ ;
    /**
     * <code>int32 id = 1;</code>
     * @return The id.
     */
    @java.lang.Override
    public int getId() {
      return id_;
    }
    /**
     * <code>int32 id = 1;</code>
     * @param value The id to set.
     * @return This builder for chaining.
     */
    public Builder setId(int value) {
      
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearId() {
      
      id_ = 0;
      onChanged();
      return this;
    }

    private int temperature_ ;
    /**
     * <code>int32 temperature = 2;</code>
     * @return The temperature.
     */
    @java.lang.Override
    public int getTemperature() {
      return temperature_;
    }
    /**
     * <code>int32 temperature = 2;</code>
     * @param value The temperature to set.
     * @return This builder for chaining.
     */
    public Builder setTemperature(int value) {
      
      temperature_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 temperature = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearTemperature() {
      
      temperature_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object uhrzeit_ = "";
    /**
     * <code>string uhrzeit = 3;</code>
     * @return The uhrzeit.
     */
    public java.lang.String getUhrzeit() {
      java.lang.Object ref = uhrzeit_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        uhrzeit_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string uhrzeit = 3;</code>
     * @return The bytes for uhrzeit.
     */
    public com.google.protobuf.ByteString
        getUhrzeitBytes() {
      java.lang.Object ref = uhrzeit_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        uhrzeit_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string uhrzeit = 3;</code>
     * @param value The uhrzeit to set.
     * @return This builder for chaining.
     */
    public Builder setUhrzeit(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      uhrzeit_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string uhrzeit = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearUhrzeit() {
      
      uhrzeit_ = getDefaultInstance().getUhrzeit();
      onChanged();
      return this;
    }
    /**
     * <code>string uhrzeit = 3;</code>
     * @param value The bytes for uhrzeit to set.
     * @return This builder for chaining.
     */
    public Builder setUhrzeitBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      uhrzeit_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object author_ = "";
    /**
     * <code>string author = 4;</code>
     * @return The author.
     */
    public java.lang.String getAuthor() {
      java.lang.Object ref = author_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        author_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string author = 4;</code>
     * @return The bytes for author.
     */
    public com.google.protobuf.ByteString
        getAuthorBytes() {
      java.lang.Object ref = author_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        author_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string author = 4;</code>
     * @param value The author to set.
     * @return This builder for chaining.
     */
    public Builder setAuthor(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      author_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string author = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearAuthor() {
      
      author_ = getDefaultInstance().getAuthor();
      onChanged();
      return this;
    }
    /**
     * <code>string author = 4;</code>
     * @param value The bytes for author to set.
     * @return This builder for chaining.
     */
    public Builder setAuthorBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      author_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object ort_ = "";
    /**
     * <code>string ort = 5;</code>
     * @return The ort.
     */
    public java.lang.String getOrt() {
      java.lang.Object ref = ort_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        ort_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string ort = 5;</code>
     * @return The bytes for ort.
     */
    public com.google.protobuf.ByteString
        getOrtBytes() {
      java.lang.Object ref = ort_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        ort_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string ort = 5;</code>
     * @param value The ort to set.
     * @return This builder for chaining.
     */
    public Builder setOrt(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      ort_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string ort = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearOrt() {
      
      ort_ = getDefaultInstance().getOrt();
      onChanged();
      return this;
    }
    /**
     * <code>string ort = 5;</code>
     * @param value The bytes for ort to set.
     * @return This builder for chaining.
     */
    public Builder setOrtBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      ort_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:messung.amt.api.Messwert)
  }

  // @@protoc_insertion_point(class_scope:messung.amt.api.Messwert)
  private static final messung.amt.api.Messwert DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new messung.amt.api.Messwert();
  }

  public static messung.amt.api.Messwert getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Messwert>
      PARSER = new com.google.protobuf.AbstractParser<Messwert>() {
    @java.lang.Override
    public Messwert parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Messwert(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Messwert> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Messwert> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public messung.amt.api.Messwert getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

