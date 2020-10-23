package com.notes.study.mysqlbinlog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

/**
 * @author jidn
 * @Date 2020/10/21
 */
public abstract class AbstractMain {

    protected static Logger logger;
    @Option(
            name = "-h",
            usage = "Print help and exits"
    )
    protected boolean help = false;

    public abstract void run();

    protected boolean preRun() {
        return true;
    }

    public void parseArgsAndRun(String[] args) {
        logger = LoggerFactory.getLogger(this.getClass());
        CmdLineParser parser = new CmdLineParser(this);
        parser.setUsageWidth(120);

        try {
            parser.parseArgument(args);
            if (this.help) {
                System.err.println("java {{cp}} " + this.getClass().getCanonicalName() + " [options...] arguments...");
                parser.printUsage(System.err);
                System.exit(1);
            } else if (this.preRun()) {
                this.run();
            } else {
                System.err.println("pre run error! exit.");
                System.exit(1);
            }
        } catch (Exception var4) {
            logger.error(var4.getMessage(), var4);
            System.exit(1);
        }

    }

}
