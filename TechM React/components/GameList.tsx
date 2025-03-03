import { useEffect, useState } from "react";
import axios from "axios";
import {
  SimpleGrid,
  Spinner,
  Box,
  Text,
  Image,
  Center,
  Badge,
  Button,
  VStack,
  Link,
} from "@chakra-ui/react";
import React from "react";

interface Game {
  id: number;
  name: string;
  background_image?: string;
  genres: { name: string }[];
  rating: number;
  released: string;
  metacritic?: number;
  platforms: { platform: { name: string } }[];
}

interface GameTrailer {
  id: number;
  name: string;
  preview: string;
  data: {
    480: string;
    max: string;
  };
}

// Using .env file to securely access API key
const API_KEY = import.meta.env.VITE_API_KEY || "";
const PAGE_SIZE = 20; // Number of games per page

const GameList: React.FC<{ searchQuery: string }> = ({ searchQuery }) => {
  const [games, setGames] = useState<Game[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);
  const [page, setPage] = useState<number>(1);
  const [nextPage, setNextPage] = useState<string | null>(null);
  const [prevPage, setPrevPage] = useState<string | null>(null);
  const [trailers, setTrailers] = useState<{ [key: number]: string | null }>(
    {}
  );
  const [gameDescriptions, setGameDescriptions] = useState<{
    [key: number]: string;
  }>({});

  // Fetch games list
  useEffect(() => {
    const fetchGames = async () => {
      setLoading(true);
      setError(null);
      try {
        const response = await axios.get(
          `https://api.rawg.io/api/games?key=${API_KEY}&page=${page}&page_size=${PAGE_SIZE}`
        );
        setGames(response.data.results);
        setNextPage(response.data.next);
        setPrevPage(response.data.previous);
      } catch (err) {
        console.error("Error fetching games:", err);
        setError("Failed to load games. Please try again.");
      } finally {
        setLoading(false);
      }
    };

    fetchGames();
  }, [page]);

  // Fetch game descriptions and trailers
  useEffect(() => {
    const fetchGameDetails = async () => {
      const newTrailers: { [key: number]: string | null } = {};
      const newDescriptions: { [key: number]: string } = {};

      await Promise.all(
        games.map(async (game) => {
          try {
            // Fetch game details for description
            const detailsResponse = await axios.get(
              `https://api.rawg.io/api/games/${game.id}?key=${API_KEY}`
            );
            newDescriptions[game.id] =
              detailsResponse.data.description_raw ||
              "No description available.";

            // Try fetching trailers from the movies endpoint
            const trailersResponse = await axios.get(
              `https://api.rawg.io/api/games/${game.id}/movies?key=${API_KEY}`
            );

            if (
              trailersResponse.data.results &&
              trailersResponse.data.results.length > 0
            ) {
              const trailerResults = trailersResponse.data
                .results as GameTrailer[];
              // Prefer YouTube trailers if available
              const youtubeTrailer = trailerResults.find(
                (trailer) =>
                  trailer.data.max.includes("youtube.com") ||
                  trailer.data.max.includes("youtu.be")
              );

              newTrailers[game.id] = youtubeTrailer
                ? youtubeTrailer.data.max
                : trailerResults[0].data.max;
            } else {
              // If no trailers found in movies endpoint, check if there's a clip in the game details
              if (detailsResponse.data.clip) {
                newTrailers[game.id] = detailsResponse.data.clip.clip || null;
              } else {
                newTrailers[game.id] = null;
              }
            }
          } catch (err) {
            console.error(`Error fetching details for game ${game.id}:`, err);
            newTrailers[game.id] = null;
            newDescriptions[game.id] = "Failed to load description.";
          }
        })
      );

      setTrailers(newTrailers);
      setGameDescriptions(newDescriptions);
    };

    if (games.length > 0) {
      fetchGameDetails();
    }
  }, [games]);

  // Filter games based on search query
  const filteredGames = games.filter((game) =>
    game.name.toLowerCase().includes(searchQuery.toLowerCase())
  );

  if (loading) {
    return (
      <Center height="100vh">
        <Spinner size="xl" color="teal.500" />
      </Center>
    );
  }

  if (error) {
    return (
      <Center height="100vh">
        <Text color="red.500">{error}</Text>
      </Center>
    );
  }

  return (
    <Box bg="black" minH="100vh" padding="20px">
      <SimpleGrid columns={[1, 2, 3, 4]} spacing="20px">
        {filteredGames.length > 0 ? (
          filteredGames.map((game) => (
            <Box
              key={game.id}
              borderRadius="lg"
              overflow="hidden"
              bg="gray.800"
              p={4}
              boxShadow="md"
              transition="transform 0.2s ease-in-out"
              _hover={{ transform: "scale(1.05)", boxShadow: "lg" }}
              height="100%"
            >
              {/* Game Image */}
              <Image
                src={game.background_image || "https://via.placeholder.com/400"}
                alt={game.name}
                borderRadius="md"
                fallbackSrc={"https://via.placeholder.com/400"}
                height="200px"
                width="100%"
                objectFit="cover"
              />

              <VStack
                align="start"
                spacing={2}
                mt={2}
                height="calc(100% - 200px)"
                justifyContent="space-between"
              >
                <Box>
                  {/* Game Title */}
                  <Text fontSize="lg" fontWeight="bold" color="white">
                    {game.name}
                  </Text>

                  {/* Game Genres */}
                  <Text fontSize="sm" color="gray.400">
                    {game.genres.map((g) => g.name).join(", ") ||
                      "No genres available"}
                  </Text>

                  {/* Game Description */}
                  <Text fontSize="sm" color="gray.300" mt={2} noOfLines={3}>
                    {gameDescriptions[game.id] || "Loading description..."}
                  </Text>

                  {/* Release Date */}
                  <Text fontSize="sm" color="gray.400" mt={2}>
                    Released: {game.released}
                  </Text>

                  {/* Metacritic Score */}
                  {game.metacritic && (
                    <Badge colorScheme="purple" fontSize="md" mt={1}>
                      Metacritic: {game.metacritic}
                    </Badge>
                  )}
                </Box>

                {/* Watch Trailer Button */}
                {trailers[game.id] ? (
                  <Link href={trailers[game.id] || ""} isExternal width="100%">
                    <Button colorScheme="red" width="100%" mt={2}>
                      🎬 Watch Trailer
                    </Button>
                  </Link>
                ) : (
                  <Button colorScheme="red" width="100%" mt={2} isDisabled>
                    No Trailer Available
                  </Button>
                )}
              </VStack>
            </Box>
          ))
        ) : (
          <Center w="100%">
            <Text fontSize="xl" color="white">
              No games found.
            </Text>
          </Center>
        )}
      </SimpleGrid>

      {/* Pagination Controls */}
      <Center mt={5}>
        <Button
          onClick={() => setPage((prev) => prev - 1)}
          isDisabled={!prevPage}
          colorScheme="teal"
          mr={3}
        >
          Previous
        </Button>
        <Text fontSize="lg" color="white" mx={3}>
          Page {page}
        </Text>
        <Button
          onClick={() => setPage((prev) => prev + 1)}
          isDisabled={!nextPage}
          colorScheme="teal"
        >
          Next
        </Button>
      </Center>
    </Box>
  );
};

export default GameList;
