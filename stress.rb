
File.open("stress_test", "r").each_line { |stress|
    File.open("media_pipe", "w") { |p|
        p.puts stress
        sleep(0.5)
    }
}
