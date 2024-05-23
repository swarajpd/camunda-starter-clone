const conventionalCommitsPresetConfig = {
  types: [
    {type: "feat", section: "Features"},
    {type: "fix", section: "Fixes"},
    {type: "chore", section: "Maintenance"},
    {type: "conf", section: "Maintenance"},
    {type: "docs", section: "Maintenance"},
    {type: "revert", section: "Fixes"},
    {type: "build", section: "Maintenance"},
    {type: "ci", hidden: true},
    {type: "style", section: "Maintenance"},
    {type: "refactor", section: "Maintenance"},
    {type: "perf", section: "Maintenance"},
    {type: "test", section: "Maintenance"}
  ]
};

module.exports = {
  branches: ["main"],
  plugins: [
    ["@semantic-release/commit-analyzer", {
      preset: "conventionalcommits",
      presetConfig: conventionalCommitsPresetConfig,
      releaseRules: [
        {breaking: true, release: "major"},
        {type: "feat", release: "minor"},
        {type: "fix", release: "patch"},
        {message: "*", release: "patch"},
      ]
    }],
    ["@semantic-release/release-notes-generator", {
      preset: "conventionalcommits",
      presetConfig: conventionalCommitsPresetConfig
    }],
    "@semantic-release/github",
    ["@semantic-release/changelog", {
      preset: "conventionalcommits",
      presetConfig: conventionalCommitsPresetConfig
    }],
    ["@semantic-release/git", {
      assets: ["CHANGELOG.md", "package.json", "package-lock.json",
        "npm-shrinkwrap.json", "**/pom.xml"]
    }],
    ["@semantic-release/exec", {
      "successCmd": "echo ${nextRelease.version} > version.txt"
    }]
  ],
  tagFormat: "${version}"
};
