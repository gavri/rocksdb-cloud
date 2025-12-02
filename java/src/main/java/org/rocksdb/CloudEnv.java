// Copyright (c) 2024-present, Rockset, Inc.  All rights reserved.
// Licensed under the Apache License 2.0 and GPLv2; you may not use this file
// except in compliance with one of these Licenses.

package org.rocksdb;

/**
 * Wrapper for a cloud-enabled {@link Env}. Use this to construct an {@link Env}
 * backed by a {@code CloudFileSystem} configuration string and then supply it
 * to {@link Options#setEnv(Env)} before opening {@link DBCloud}.
 */
public class CloudEnv extends RocksEnv {
  private CloudEnv(final long nativeHandle) {
    super(nativeHandle);
  }

  /**
   * Create a cloud-enabled Env by parsing the provided configuration string.
   *
   * @param baseEnv base {@link Env} to delegate time/thread ops to (usually
   *     {@link Env#getDefault()}).
   * @param config configuration string understood by {@code CloudFileSystemEnv}
   *     (for example: {@code "id=cloud;src_bucket=...;dest_bucket=..."}).
   * @return a new {@link CloudEnv} instance.
   * @throws RocksDBException if the configuration cannot be parsed or applied.
   */
  public static CloudEnv create(final Env baseEnv, final String config)
      throws RocksDBException {
    return new CloudEnv(createFromString(baseEnv.nativeHandle_, config, true));
  }

  /**
   * Create a cloud-enabled Env without preparing options. This is intended for
   * advanced use-cases where the caller wants to control option preparation.
   *
   * @param baseEnv base {@link Env}.
   * @param config configuration string.
   * @param invokePrepareOptions whether to invoke option preparation.
   * @return a new {@link CloudEnv}.
   * @throws RocksDBException if the configuration cannot be parsed or applied.
   */
  public static CloudEnv create(final Env baseEnv, final String config,
      final boolean invokePrepareOptions) throws RocksDBException {
    return new CloudEnv(
        createFromString(baseEnv.nativeHandle_, config, invokePrepareOptions));
  }

  private static native long createFromString(long baseEnvHandle, String config,
      boolean invokePrepareOptions) throws RocksDBException;
}
