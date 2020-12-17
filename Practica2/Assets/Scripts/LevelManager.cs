using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace MazesAndMore
{

    public class LevelManager : MonoBehaviour
    {
        [Tooltip("Manager del tablero")]
        public MazeManager mazeManager;

        public TextAsset level;

        // Start is called before the first frame update
        void Start()
        {
            if (mazeManager == null)
                Debug.Log("Me falta el MazeManager");
        }

        // Update is called once per frame
        void Update()
        {

        }
    }
}
