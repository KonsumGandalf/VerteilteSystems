// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service.proto

package messung.amt.api;

public interface MesswertOrBuilder extends
    // @@protoc_insertion_point(interface_extends:messung.amt.api.Messwert)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 id = 1;</code>
   * @return The id.
   */
  int getId();

  /**
   * <code>int32 temperature = 2;</code>
   * @return The temperature.
   */
  int getTemperature();

  /**
   * <code>string uhrzeit = 3;</code>
   * @return The uhrzeit.
   */
  java.lang.String getUhrzeit();
  /**
   * <code>string uhrzeit = 3;</code>
   * @return The bytes for uhrzeit.
   */
  com.google.protobuf.ByteString
      getUhrzeitBytes();

  /**
   * <code>string author = 4;</code>
   * @return The author.
   */
  java.lang.String getAuthor();
  /**
   * <code>string author = 4;</code>
   * @return The bytes for author.
   */
  com.google.protobuf.ByteString
      getAuthorBytes();

  /**
   * <code>string ort = 5;</code>
   * @return The ort.
   */
  java.lang.String getOrt();
  /**
   * <code>string ort = 5;</code>
   * @return The bytes for ort.
   */
  com.google.protobuf.ByteString
      getOrtBytes();
}
