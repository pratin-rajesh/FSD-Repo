import { IconButton, useColorMode } from "@chakra-ui/react";
import { FaSun, FaMoon } from "react-icons/fa";

const DarkSwitch: React.FC = () => {
  const { colorMode, toggleColorMode } = useColorMode(); // Chakra UI's dark mode switch

  return (
    <IconButton
      aria-label="Toggle Dark Mode"
      icon={colorMode === "light" ? <FaMoon /> : <FaSun />}
      onClick={toggleColorMode}
      variant="ghost"
      color="white"
      _hover={{ bg: "gray.700" }}
    />
  );
};

export default DarkSwitch;
