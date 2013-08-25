#TODO: separate out basics that pants needed to run, from user dependencies
def make_lib(name, rev, org='com.twitter'):
  return jar_library(name=name,
    dependencies=[
      jar(org=org, name=name, rev=rev).with_sources()
    ]
  )

def alias(source, target):
  return jar_library(name = target,
    dependencies = [ pants(':' + source) ])


def twitter_lib(name, rev):
  return make_lib(name, rev, 'com.twitter')

def scala_lib(name, rev):
  return make_lib(name, rev, 'org.scala-lang')

scala_lib('jline', '2.10.2')
scala_lib('scala-library', '2.10.2')
scala_lib('scala-compiler', '2.10.2')
scala_lib('scala-reflect', '2.10.2')
scala_lib('scalap', '2.10.2')

make_lib('commons-io', '2.1', 'commons-io')
make_lib('commons-codec', '1.8', 'commons-codec')
make_lib('commons-codec', '1.5', 'commons-codec')
make_lib('commons-collections', '3.2.1', 'commons-collections')
make_lib('commons-lang', '2.6', 'commons-lang')
make_lib('commons-pool', '1.5.4', 'commons-pool')

make_lib('junit', '4.10', 'junit')
make_lib('mockito-all', '1.9.5', 'org.mockito')
make_lib('specs_2.10', '1.6.9', 'org.scala-tools.testing')
make_lib('scalacheck_2.10', '1.10.0', 'org.scalacheck')
make_lib('scalatest_2.10', '1.9.1', 'org.scalatest')
alias('mockito-all', 'mockito')
alias('specs_2.10', 'specs')

SLF4J_REV = '1.6.1'

make_lib('slf4j-api', SLF4J_REV, 'org.slf4j')
make_lib('slf4j-nop', SLF4J_REV, 'org.slf4j')

UTIL_REV = '6.3.7'
FINAGLE_REV = '6.5.2'
SCROOGE_REV = '3.6.0'
STOREHAUS_REV = '0.4.1'

twitter_lib('util-core_2.10', UTIL_REV)
twitter_lib('util-codec_2.10', UTIL_REV)
twitter_lib('util-thrift_2.10', UTIL_REV)
twitter_lib('util-jvm_2.10', UTIL_REV)
alias('util-core_2.10', 'util-core')
alias('util-jvm_2.10', 'util-jvm')

twitter_lib('finagle-core_2.10', FINAGLE_REV)
twitter_lib('finagle-memcached_2.10', FINAGLE_REV)
twitter_lib('finagle-http_2.10', FINAGLE_REV)
twitter_lib('finagle-thrift_2.10', FINAGLE_REV)

twitter_lib('scrooge-core_2.10', SCROOGE_REV)
twitter_lib('scrooge-runtime_2.10', SCROOGE_REV)
twitter_lib('scrooge-serializer_2.10', SCROOGE_REV)

twitter_lib('storehaus-cache_2.10', STOREHAUS_REV)
twitter_lib('storehaus-core_2.10', STOREHAUS_REV)
twitter_lib('storehaus-memcache_2.10', STOREHAUS_REV)

twitter_lib('chill_2.10', '0.2.0')

twitter_lib('twitter-server_2.10', '1.0.3')

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

make_lib('jsr305', '1.3.9', 'com.google.code.findbugs')

jar_library(
  name= 'guava',
  dependencies=[
    jar(org='com.google.guava', name='guava', rev='14.0.1').with_sources(),
    jar(org='javax.inject', name='javax.inject', rev='1').with_sources(),

    # Defined in provided scope so we provide here.
    pants(':jsr305'),
  ]
)

jar_library(name = 'guice',
            dependencies = [
              jar(
                org = 'com.google.inject', name = 'guice', rev = '3.0',
                apidocs = 'http://google-guice.googlecode.com/svn/tags/3.0/javadoc/'
              ).with_sources()
            ]
           )

jar_library(name = 'jetty',
            dependencies = [
              jar(org = 'org.mortbay.jetty', name = 'jetty', rev = '6.1.25').with_sources()
            ]
           )

jar_library(name = 'servlet-api',
            dependencies = [
              jar(org = 'javax.servlet', name = 'servlet-api', rev = '2.5').with_sources()
            ]
           )

jar_library(name = 'stringtemplate',
            dependencies = [
              jar(org = 'org.antlr', name = 'stringtemplate', rev = '3.2.1').with_sources()
            ]
           )

jar_library(name = 'gson',
            dependencies = [
              jar(org = 'com.google.code.gson', name = 'gson', rev = '2.2.2').with_sources()
            ])

jar_library(name = 'guice-multibindings',
            dependencies = [
              jar(org = 'com.google.inject.extensions', name = 'guice-multibindings', rev = '3.0')
                .with_sources()
            ]
           )

jar_library(name = 'guice-servlet',
            dependencies = [
              jar(org = 'com.google.inject.extensions', name = 'guice-servlet', rev = '3.0')
                .with_sources()
            ]
           )

jar_library(name = 'guice-throwingproviders',
            dependencies = [
              jar(org = 'com.google.inject.extensions', name = 'guice-throwingproviders', rev = '3.0')
                .with_sources()
            ]
           )

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

jar_library(
  name='protobuf-2.4.1',
  dependencies=[
    jar(org='com.google.protobuf', name='protobuf-java', rev='2.4.1').with_sources(),
  ]
)

jar_library(
  name='libthrift-0.5.0',
  dependencies=[
    jar(org='org.apache.thrift', name='libthrift', rev='0.5.0-1'),
  ]
)

jar_library(
  name='thrift-0.5.0',
  dependencies=[
    pants(':commons-lang'),
    pants(':libthrift-0.5.0'),
    pants(':slf4j-api'),
  ]
)

alias('thrift-0.5.0', 'thrift')

jar_library(name = 'joda-time',
            dependencies = [
              jar(org = 'joda-time', name = 'joda-time', rev = '2.1'),
              jar(org = 'org.joda', name = 'joda-convert', rev = '1.2')
            ]
           )

jar_library(name = 'spy-memcached',
            dependencies = [
              jar(org = 'spy', name = 'spymemcached', rev = '2.8.1').with_sources()
            ]
           )

jar_library(name = 'easymock',
            dependencies = [
              jar(org = 'org.easymock', name = 'easymock', rev = '3.1').with_sources()
            ]
           )

jar_library(name = 'tl4j',
            dependencies = [
              jar(org = 'com.google.testing', name = 'test-libraries-for-java', rev = '1.1.1')
                # tl4j by default pulls in junit-4.6, which conflicts with 4.10 needed by our
                # testing code (e.g. ConcurrentJunitRunner). So exclude it here so that the junit
                # target defined above is used instead. tl4j seems to be happy with junit-4.10.
                .exclude(org = 'junit', name='junit')
            ]
           )


jar_library(name = 'args4j',
            dependencies = [ jar(org = 'args4j', name = 'args4j', rev = '2.0.16').with_sources() ])

