import { extendTheme, ThemeConfig } from "@chakra-ui/react";

const config: ThemeConfig = {
  initialColorMode: "light",
  useSystemColorMode: false,
};

const theme = extendTheme({
  config,
  fonts: {
    heading: "'SF-Pro', sans-serif",
    body: "'SF-Pro', sans-serif",
  },
});

export default theme;
