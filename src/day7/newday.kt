package day7

import Puzzle


sealed class FilesystemEntry(val name: String) {
    abstract fun getSize(): Int
}

class File(name: String, private val size: Int) : FilesystemEntry(name) {
    override fun getSize(): Int = size
}

class Directory(name: String) : FilesystemEntry(name) {
    private val elements: MutableMap<String, FilesystemEntry> = mutableMapOf()

    override fun getSize(): Int = elements
        .filter { it.key != ".." }
        .values
        .sumOf(FilesystemEntry::getSize)

    fun subDir(name: String): Directory {
        val find = elements[name]
        if (find is Directory) {
            return find
        }
        throw RuntimeException("$name is not a directory")
    }

    fun addChild(child: FilesystemEntry) {
        elements[child.name] = child
    }

    fun addParent(parent: Directory) {
        elements[".."] = parent
    }

    fun subDirs(): List<Directory> {
        return elements
            .filterKeys { it != ".." }
            .values
            .filterIsInstance<Directory>()
    }
}

fun sumSizes(dir: Directory): Int {
    var sum = 0
    if (dir.getSize() <= 100_000) {
        sum += dir.getSize()
    }
    sum += dir.subDirs()
        .map(::sumSizes)
        .sum()

    return sum
}

fun flatDirs(dir: Directory): List<Directory> {
    val mutableList = dir.subDirs()
        .flatMap { flatDirs(it) }
        .toMutableList()
    mutableList.add(dir)
    return mutableList
}

private fun readFileSystem(input: List<String>): Directory {
    val root = Directory("/")
    var pwd = root
    input.forEach {
        when {
            it.startsWith("$ cd /") -> {
                // ignore first line
            }

            it.startsWith("$ cd") -> {
                pwd = pwd.subDir(it.substring(5))
            }

            it.startsWith("$ ls") -> {
                // ignore
            }

            it.startsWith("dir") -> {
                val newDir = Directory(it.substring(4))
                newDir.addParent(pwd)
                pwd.addChild(newDir)
            }

            else -> { // file entry
                val properties = it.split(" ")
                val file = File(properties[1], properties[0].toInt())
                pwd.addChild(file)
            }
        }
    }
    return root
}

fun part1(input: List<String>): Int {
    val root = readFileSystem(input)

    return sumSizes(root)
}

fun part2(input: List<String>): Int {
    val root = readFileSystem(input)

    val totalSpace = 70_000_000
    val requiredSpace = 30_000_000
    val usedSpace = root.getSize()
    val spaceToFree = requiredSpace - (totalSpace - usedSpace)

    return flatDirs(root)
        .filter { it.getSize() >= spaceToFree }
        .minOf { it.getSize() }
}

fun main() {
    Puzzle(
        "day7",
        ::part1,
        95437,
        ::part2,
        24933642
    ).test()
}
