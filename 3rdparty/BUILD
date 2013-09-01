# Note: those are basic dependencies for pants to build.
# TODO: separate out basics that pants needed to run, from user dependencies
def make_lib(org, name, rev, alias=None):
  if alias == None:
    alias = name
  jar_library(name=alias,
    dependencies=[
      jar(org=org, name=name, rev=rev).with_sources()
    ]
  )

def alias(source, target):
  jar_library(name = target,
    dependencies = [ pants(':' + source) ])

def versioned_scala(org, name, rev):
  make_lib(org, name, rev)
  a = '_'.join(name.split('_')[0:-1])
  alias(name, a)

# Twitter scala lib
def twitter_scala(name, rev):
  versioned_scala('com.twitter', name, rev)

def scala_lib(name, rev):
  make_lib('org.scala-lang', name, rev)

SLF4J_REV = '1.6.1'
UTIL_REV = '6.3.7'
FINAGLE_REV = '6.5.2'
SCROOGE_REV = '3.6.0'
STOREHAUS_REV = '0.4.1'

scala_lib('jline', '2.10.2')
scala_lib('scala-library', '2.10.2')
scala_lib('scala-compiler', '2.10.2')
scala_lib('scala-reflect', '2.10.2')
scala_lib('scalap', '2.10.2')

make_lib('commons-io', 'commons-io', '2.1')
make_lib('commons-codec', 'commons-codec', '1.8')
make_lib('commons-codec', 'commons-codec', '1.5')
make_lib('commons-collections', 'commons-collections', '3.2.1')
make_lib('commons-lang', 'commons-lang', '2.6')
make_lib('commons-pool', 'commons-pool', '1.5.4')

make_lib('junit', 'junit', '4.10')
make_lib('org.mockito', 'mockito-all', '1.9.5')
make_lib('org.scala-tools.testing', 'specs_2.10', '1.6.9')
alias('mockito-all', 'mockito')
alias('specs_2.10', 'specs')

make_lib('org.slf4j', 'slf4j-api', SLF4J_REV)
make_lib('org.slf4j', 'slf4j-nop', SLF4J_REV)

twitter_scala('util-core_2.10', UTIL_REV)
twitter_scala('util-codec_2.10', UTIL_REV)
twitter_scala('util-jvm_2.10', UTIL_REV)
twitter_scala('util-thrift_2.10', UTIL_REV)

twitter_scala('finagle-core_2.10', FINAGLE_REV)
twitter_scala('finagle-memcached_2.10', FINAGLE_REV)
twitter_scala('finagle-http_2.10', FINAGLE_REV)
twitter_scala('finagle-thrift_2.10', FINAGLE_REV)

twitter_scala('scrooge-core_2.10', SCROOGE_REV)
twitter_scala('scrooge-runtime_2.10', SCROOGE_REV)
twitter_scala('scrooge-serializer_2.10', SCROOGE_REV)

twitter_scala('storehaus-cache_2.10', STOREHAUS_REV)
twitter_scala('storehaus-core_2.10', STOREHAUS_REV)
twitter_scala('storehaus-memcache_2.10', STOREHAUS_REV)

twitter_scala('chill_2.10', '0.2.0')

twitter_scala('twitter-server_2.10', '1.0.3')

jar_library(name='tests',
  dependencies=[
    pants('3rdparty:junit'),
    pants('3rdparty:mockito-all'),
    pants('3rdparty:specs_2.10'),
  ]
)

jar_library(name='scala-runtime',
  dependencies=[
    pants('3rdparty:scala-compiler'),
    pants('3rdparty:scala-library'),
    pants('3rdparty:scala-reflect'),
  ]
)

make_lib('com.google.code.findbugs', 'jsr305', '1.3.9')

jar_library(
  name= 'guava',
  dependencies=[
    jar(org='com.google.guava', name='guava', rev='14.0.1').with_sources(),
    jar(org='javax.inject', name='javax.inject', rev='1').with_sources(),

    # Defined in provided scope so we provide here.
    pants(':jsr305'),
  ]
)

make_lib('com.google.inject', 'guice', '3.0')
make_lib('org.mortbay.jetty', 'jetty', '6.1.25')
make_lib('javax.servlet', 'servlet-api', '2.5')
make_lib('org.antlr', 'stringtemplate', '3.2.1')
make_lib('com.google.code.gson', 'gson', '2.2.2')
make_lib('com.google.inject.extensions', 'guice-multibindings', '3.0')
make_lib('com.google.inject.extensions', 'guice-servlet', '3.0')
make_lib('com.google.inject.extensions', 'guice-throwingproviders', '3.0')

jar_library(
  name='antlr-3.4',
  dependencies=[
    jar(org='org.antlr', name='antlr', rev='3.4').with_sources(),
    jar(org='org.antlr', name='antlr-runtime', rev='3.4').with_sources(),
  ]
)

jar_library(
  name='antlr4',
  dependencies=[
    jar(org='org.antlr', name='antlr4', rev='4.0').with_sources(),
    jar(org='org.antlr', name='antlr4-runtime', rev='4.0').with_sources(),
  ]
)

jar_library(
  name='thrift-0.5.0-finagle',
  dependencies=[
    pants(':thrift-0.5.0'),
    pants(':finagle-core_2.10'),
    pants(':finagle-thrift_2.10'),
    pants(':util-core_2.10'),
  ]
)

make_lib('com.google.protobuf', 'protobuf-java', '2.4.1', 'protobuf-2.4.1')
make_lib('org.apache.thrift', 'libthrift', '0.5.0-1', 'libthrift-0.5.0')

jar_library(
  name='thrift-0.5.0',
  dependencies=[
    pants(':commons-lang'),
    pants(':libthrift-0.5.0'),
    pants(':slf4j-api'),
  ]
)

alias('thrift-0.5.0-finagle', 'thrift-finagle')
alias('thrift-0.5.0', 'thrift')

jar_library(name = 'joda-time',
            dependencies = [
              jar(org = 'joda-time', name = 'joda-time', rev = '2.1'),
              jar(org = 'org.joda', name = 'joda-convert', rev = '1.2')
            ]
           )

make_lib('spy', 'spymemcached', '2.8.1', 'spy-memcached')
make_lib('org.easymock', 'easymock', '3.1')

jar_library(name = 'tl4j',
            dependencies = [
              jar(org = 'com.google.testing', name = 'test-libraries-for-java', rev = '1.1.1')
                # tl4j by default pulls in junit-4.6, which conflicts with 4.10 needed by our
                # testing code (e.g. ConcurrentJunitRunner). So exclude it here so that the junit
                # target defined above is used instead. tl4j seems to be happy with junit-4.10.
                .exclude(org = 'junit', name='junit')
            ]
           )

make_lib('args4j', 'args4j', '2.1.16')

versioned_scala('com.typesafe.akka', 'akka-actor_2.10', '2.2.1')
