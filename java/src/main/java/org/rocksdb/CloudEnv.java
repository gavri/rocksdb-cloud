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
  CloudEnv(final long nativeHandle) {
    super(nativeHandle);
  }

  CloudEnv(final Env baseEnv, final String config,
      final boolean invokePrepareOptions) throws RocksDBException {
    super(createFromString(baseEnv.nativeHandle_, config, invokePrepareOptions));
  }

  static CloudEnv create(final Env baseEnv, final String config,
      final boolean invokePrepareOptions) throws RocksDBException {
    return new CloudEnv(baseEnv, config, invokePrepareOptions);
  }

  private static native long createFromString(long baseEnvHandle, String config,
      boolean invokePrepareOptions) throws RocksDBException;
}
