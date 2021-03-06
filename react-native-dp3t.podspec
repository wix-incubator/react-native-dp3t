require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "react-native-dp3t"
  s.version      = package["version"]
  s.summary      = package["description"]
  s.description  = <<-DESC
                  react-native-dp3t
                   DESC
  s.homepage     = "https://github.com/github_account/react-native-dp3t"
  # brief license entry:
  s.license      = "MIT"
  # optional - use expanded license entry instead:
  # s.license    = { :type => "MIT", :file => "LICENSE" }
  s.authors      = { "Lev" => "yourname@email.com" }
  s.platforms    = { :ios => "10.0" }
  s.source       = { :git => "https://github.com/github_account/react-native-dp3t.git", :tag => "#{s.version}" }

  s.source_files = "lib/ios/**/*.{h,m,swift}"
  s.requires_arc = true

  s.dependency "React"
  # ...
  # s.dependency "..."
end

