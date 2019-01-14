/*
 * Copyright (c) 2018 Hai Zhang <dreaming.in.code.zh@gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.files.provider.archive;

import org.apache.commons.compress.archivers.ArchiveEntry;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java8.nio.file.Path;
import java8.nio.file.attribute.FileTime;
import me.zhanghai.android.files.provider.common.PosixFileAttributeView;
import me.zhanghai.android.files.provider.common.PosixFileModeBit;
import me.zhanghai.android.files.provider.common.PosixGroup;
import me.zhanghai.android.files.provider.common.PosixUser;

public class ArchiveFileAttributeView implements PosixFileAttributeView {

    private static final String NAME = ArchiveFileSystemProvider.SCHEME;

    static final Set<String> SUPPORTED_NAMES = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("basic", "posix", NAME)));

    @NonNull
    private final Path mPath;

    @NonNull
    private final ArchiveFileSystem mFileSystem;

    ArchiveFileAttributeView(@NonNull Path path) {
        mPath = path;

        mFileSystem = (ArchiveFileSystem) mPath.getFileSystem();
    }

    @NonNull
    @Override
    public String name() {
        return NAME;
    }

    @NonNull
    @Override
    public ArchiveFileAttributes readAttributes() throws IOException {
        ArchiveEntry entry = mFileSystem.getEntry(mPath);
        return new ArchiveFileAttributes(mFileSystem.getArchiveFile(), entry);
    }

    @Override
    public void setTimes(@Nullable FileTime lastModifiedTime, @Nullable FileTime lastAccessTime,
                         @Nullable FileTime createTime) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setOwner(@NonNull PosixUser owner) {
        Objects.requireNonNull(owner);
        throw new UnsupportedOperationException();
    }

    @Override
    public void setGroup(@NonNull PosixGroup group) {
        Objects.requireNonNull(group);
        throw new UnsupportedOperationException();
    }

    @Override
    public void setMode(@NonNull Set<PosixFileModeBit> mode) {
        Objects.requireNonNull(mode);
        throw new UnsupportedOperationException();
    }
}
