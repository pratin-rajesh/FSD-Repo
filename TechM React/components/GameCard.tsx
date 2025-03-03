import React, { useEffect, useState } from "react";
import {
  Box,
  Image,
  Text,
  Button,
  VStack,
  Link,
  useColorModeValue,
} from "@chakra-ui/react";

interface Game {
  id: number;
  name: string;
  background_image: string;
  metacritic?: number;
  description_raw?: string;
  genres: { name: string }[];
  released?: string;
  clip?: { clips?: { clip: string }[] }; // Fixed clip structure
  stores?: { store: { name: string }; url: string }[];
}

interface GameCardProps {
  game: Game;
}

// Make sure you have your .env file set up with VITE_API_KEY
const API_KEY = import.meta.env.VITE_API_KEY;

const GameCard: React.FC<GameCardProps> = ({ game }) => {
  const [description, setDescription] = useState<string | null>(null);
  const bgColor = useColorModeValue("gray.100", "gray.900");

  // Fetch game details from RAWG API
  useEffect(() => {
    const fetchGameDetails = async () => {
      try {
        const response = await fetch(
          `https://api.rawg.io/api/games/${game.id}?key=${API_KEY}`
        );
        if (!response.ok) {
          throw new Error(`API responded with status: ${response.status}`);
        }
        const data = await response.json();
        setDescription(data.description_raw || "No description available.");
      } catch (error) {
        console.error("Error fetching game details:", error);
        setDescription("Failed to load description.");
      }
    };

    if (game.id) {
      fetchGameDetails();
    }
  }, [game.id]);

  // Function to get the trailer URL (YouTube or Steam)
  const getTrailerUrl = (): string | undefined => {
    // The clip structure in RAWG API might have changed
    if (game.clip?.clips && game.clip.clips.length > 0)
      return game.clip.clips[0].clip;

    const steamStore = game.stores?.find(
      (store) => store.store.name === "Steam"
    );
    return steamStore ? steamStore.url : undefined;
  };

  return (
    <Box
      p={4}
      borderWidth="1px"
      borderRadius="lg"
      overflow="hidden"
      bg={bgColor}
      width="300px"
      boxShadow="lg"
      className="game-card" // Added class for custom styling with the font
    >
      {/* Game Image */}
      {game.background_image && (
        <Image
          src={game.background_image}
          alt={game.name}
          borderRadius="md"
          fallback={<Box height="150px" bg="gray.300" borderRadius="md" />}
        />
      )}

      {/* Game Info */}
      <VStack align="start" spacing={2} mt={2}>
        <Text fontSize="xl" fontWeight="bold" className="sf-pro-text">
          {game.name}
        </Text>
        <Text fontSize="sm" color="gray.500">
          {game.genres && game.genres.length > 0
            ? game.genres.map((g) => g.name).join(", ")
            : "No genres available"}
        </Text>

        {/* Metacritic Score */}
        {game.metacritic && (
          <Text fontSize="md" fontWeight="bold" color="green.400">
            Metacritic: {game.metacritic}
          </Text>
        )}

        {/* Game Description */}
        <Text fontSize="sm" noOfLines={3}>
          {description || "Loading description..."}
        </Text>

        {/* Trailer Button (Only if URL Exists) */}
        {getTrailerUrl() && (
          <Link href={getTrailerUrl()} isExternal>
            <Button colorScheme="red" size="sm">
              🎬 Watch Trailer
            </Button>
          </Link>
        )}
      </VStack>
    </Box>
  );
};

export default GameCard;
